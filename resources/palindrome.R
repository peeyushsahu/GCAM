#GeneMinerData <- read.csv(file.choose(),header=TRUE,stringsAsFactor=FALSE)
#SynonymList <- readLines(file.choose(),header=FALSE,stringsAsFactor=FALSE)
path = "/home/peeyush/GCAM_output"
dirpath = "/home/peeyush/Documents/GCAM-1.0"
Pthreshold = 0.001
Ethreshold = 0.3
synonym = 1
test = "binomial"
palindrome <- function(path, dirpath, Pthreshold, Ethreshold, synonym, test) {
  ### Loading package for heatmap
  if (!require("gplots")) {
    install.packages("gplots", dependencies = TRUE)
    library(gplots)
  }
  if (!require("scrime")) {
    install.packages("scrime", dependencies = TRUE)
    library(scrime)
  }
  
  #### Conversion of Data.frame into matrix
  path1 = paste(path,"/firstResult.csv",sep="")
  if(synonym == 1){
    path2 = paste(path,"/synonymList.csv",sep="")
    SynonymList <- readLines(path2)
  }
  path3 = paste(dirpath,"/resources/cell_type_synonyms.csv",sep="")
  GeneMinerData <- read.csv(path1,header=TRUE,stringsAsFactor=FALSE)
  cellTypeSynonym <- read.csv(path3,header=FALSE,stringsAsFactor=FALSE)
  
  #### Remove duplicates
  
  findU <- GeneMinerData[,1]
  ind <- 0
  duplicates <- c()
  for(i in 1:length(findU)){
    index = which(tolower(findU) == tolower(findU[i]))
    if(length(index) > 1){
      for(j in 2:length(index)){
        duplicates[ind] <- index [j]
        ind = ind+1
      }
    }
    #print(index)
  }
  if(length(duplicates) != 0){
    GeneMinerData <- GeneMinerData[-unique(duplicates),]   
  }
   
  #### converting list to list of lists
  if(synonym == 1){
  geneRows <- c()
  SynonymList <- unique(SynonymList)
  for(i in 1:length(SynonymList)){
    SynonymList[i] <- strsplit(SynonymList[[i]], ",")
    geneRows[i] <- SynonymList[[i]][1]
  }
  }
  
  #### Join column with synonym cell names
  indextoRemove = c()
  indRemove = 1
  #newMatrixcellsynonym <- matrix(data=0,nrow=length(cellTypeSynonym[,1]),ncol=length(GeneMinerData[,1]))
  for(i in 1:dim(cellTypeSynonym)[1]){  
    indAdCell = 1
    indexCellType <- c()
    for(j in 1:dim(cellTypeSynonym)[2]){
      if(cellTypeSynonym[i,j]!=""){
        geneMinerDataCol <- gsub("[.]"," ",tolower(colnames(GeneMinerData)))
        index = which(geneMinerDataCol == tolower(cellTypeSynonym[i,j]))
        #print(geneMinerDataCol[index])
        if(length(index) > 0){
          newIndex <- index
          #print(cellTypeSynonym[i,j])
          indexCellType[indAdCell] <- newIndex
          if(j == 1){
            pos2Replace <- newIndex
            rowToAdd <- GeneMinerData[newIndex]
            #print(head(rowToAdd))
          }else{
            rowToAdd <- rowToAdd + GeneMinerData[newIndex]
          }
          if(j != 1){
            #print(newIndex)
            #print(newIndex)
            indextoRemove[indRemove] <- newIndex
            indRemove = indRemove + 1
          }
        }
      }
    }
    GeneMinerData[pos2Replace] <- rowToAdd
    #print(rowToAdd)
  }
  GeneMinerData <- GeneMinerData[,-indextoRemove]
  
  #### Joining counts for GENE synonyms 
  if(synonym == 1){
  newMatrix.synonym <- matrix(data=0,nrow=length(SynonymList),ncol=length(GeneMinerData[1,])-1)
  for(i in 1:length(SynonymList)){
    for(j in 1:length(SynonymList[[i]])){ 
      if(SynonymList[[i]][j]!=""){
      index = which(tolower(GeneMinerData[,1]) == tolower(SynonymList[[i]][j]))
      if(length(index) != 0){
      #print(index)
      for(k in 2:dim(GeneMinerData)[2]){
        #print(GeneMinerData[index[1],k])
        #print(newMatrix.synonym[i,k-1])
        newMatrix.synonym[i,k-1] <- newMatrix.synonym[i,k-1] + GeneMinerData[index[1],k]
        }
      }
    }
  }
  }
  ### for synonym
  #### Coverting dataframe to matrix
  rownames(newMatrix.synonym) <- geneRows
  GCN <- colnames(GeneMinerData)
  colnames(newMatrix.synonym) <- GCN[-1]
  mat <- newMatrix.synonym
  } 
  
  else{
      
    #### Coverting dataframe to matrix
    #index = c()
    #for(i in 1:length(SynonymList)){
      #print(i)
    #  index[i]  = which(tolower(GeneMinerData[,1]) == tolower(SynonymList[[i]][1]))
    #}
    #GeneMinerData <- GeneMinerData[index,]
    mat <- as.matrix(sapply(GeneMinerData[,-1], as.numeric))
    rownames(mat) <- GeneMinerData[,1]
    mat[is.na(mat)] <- 0 
  } 
  
  #### reduce matrix size by removing non-significant celltypes ####
  rowSum <- rowSums(mat)
  for(i in 1:dim(mat)[1]){
    for(j in 1:dim(mat)[2]){
      #print(i)
      #print(j)
      if(mat[i,j]/rowSum[[i]] < 0.05 || is.na(mat[i,j]/rowSum[[i]]) ){
        mat[i,j] <- 0
        #print(mat[i,j])
      }
    }
  }
  
  #### To find columns with total zero (genes with no cellType occurrence)
  colSum <- colSums(mat)
  a <- which(colSum==0)
  if(length(a) != 0){
    Clist <- c()
    j = 1
    for(i in 1 : length(colSum)){  
      if(colSum[i] == 0){
        Clist[j] <- i
        j = j + 1
      }
    }
    mat <- mat[,-Clist]
  }
  colSum1 <- colSums(mat)
 
  #### Finding row with zero values (cellTypes with no occurrence)
  b <- which(rowSum==0)
  if(length(b)!=0){
    Rlist <- c()
    j = 1
    for(i in 1 : length(rowSum)){  
      if(rowSum[i] < 5){
        Rlist[j] <- i
        j = j + 1
      }
    }
    mat <- mat[-Rlist,]
  }
  
  mat4pVal <- mat ## copy for Pvalue calculations
  
  ### pValues are being calculated with fisher exact test 
  ### matrix -> pValue.mat contains pValues
  
  ##################################################################
  #                   STATISTICAL TEST                             #
  # a <- ocurrence of cell type of interest for gene of interest   #
  # b <- total occurrence for a gene of interest                   #
  # c <- total occurrence for the cell type of interest            #
  # d <- total occurence for all gene                              #
  #                                                                #
  ##################################################################
  
  pValue.mat <- matrix(0,nrow=dim(mat4pVal)[1],ncol=dim(mat4pVal)[2])
  if(test == "binomial"){
    for(i in 1:dim(mat4pVal)[[1]]){
      for(j in 1:dim(mat4pVal)[[2]]){
    
    a <- mat4pVal[i,j]
    b <- rowSums(mat4pVal)[[i]]-mat4pVal[i,j]
    c <- colSum1[[j]]-mat4pVal[i,j]
    d <- Reduce("+",rowSums(mat4pVal))-(a+b+c)
        
    Zscore = (a/c-b/d)/sqrt((b/d*(1-b/d))/d)
    
    if(is.nan(Zscore) || is.infinite(Zscore)){
      Zscore = 0
    }
    pValue.mat[i,j] <- Zscore
      }
    }
    #sd <- sd(pValue.mat)
    #mean <- mean(pValue.mat)
    #pValue.mat <- apply(pValue.mat,2,function(x) (x <- pnorm(x, mean = mean, sd = sd, lower.tail=FALSE)))
    mat.rowname <- rownames(mat)
    mat.colname <- colnames(mat)
    enrichment.result <- data.frame(Gene=character(1),cellType=character(1),Enrichment=numeric(1),Enrichment_Score=numeric(1), stringsAsFactors = FALSE)
    
    for(i in 1:dim(pValue.mat)[[1]]){
      for(j in 1:dim(pValue.mat)[[2]]){
        #if(mat[i,j] > Pthreshold){
        if(pValue.mat[i,j] > 50){  
          newrow <- list(mat.rowname[[i]],mat.colname[[j]],mat[i,j],pValue.mat[i,j])
          enrichment.result = rbind(enrichment.result,newrow)
        }
      }
    }
    
    write.csv(enrichment.result, file = paste(path,"/enrichment_binomial.csv",sep=""),na = "NA", row.names = TRUE)
    
  }
  
  else{
  oddRatio.mat <- matrix(0,nrow=dim(mat4pVal)[1],ncol=dim(mat4pVal)[2])
  for(i in 1:dim(mat4pVal)[[1]]){
    for(j in 1:dim(mat4pVal)[[2]]){
      
      a <- mat4pVal[i,j]
      b <- rowSums(mat4pVal)[[i]]-mat4pVal[i,j]
      c <- colSum1[[j]]-mat4pVal[i,j]
      d <- Reduce("+",rowSums(mat4pVal))-(a+b+c)
      #(rowSums(mat4pVal)[[i]]+r.count.s.celltype.a.gene)
      if(a > 0){
      contigency <-
        matrix(c(a,c,b,d),
               nrow = 2,
               dimnames =
                 list(c("Perticular.gene", "rest.gene"),
                      c("perticular.cellType", "rest.cellType")))
      #print(contigency)
      pval = fisher.test(contigency, alternative="two.sided")
      #print(pval[[4]][[1]])
      pValue.mat[i,j] <- pval[[1]]
      oddRatio.mat[i,j] <- pval[[3]][[1]]
    }
    else{
      pValue.mat[i,j] <- 1
      oddRatio.mat[i,j] <- 0
    }
  }
  }
  ### For calculating ADJUSTED PValues with multiple correction
  
  ### matrix -> Adjusted.Pvalue contains Adjusted pValues
  PValue.Data.frame <-  as.data.frame(pValue.mat)
  rownames(PValue.Data.frame) <- rownames(mat4pVal)
  colnames(PValue.Data.frame) <- colnames(mat4pVal)
  
  Adjusted.Pvalue <- pValue.mat
  FDR = 0.05
  No.of.cellTypes = dim(pValue.mat)[[2]]
  for(i in 1:dim(pValue.mat)[[1]]){
    for(j in 1:dim(pValue.mat)[[2]]){
      
      if(pValue.mat[i,j] < FDR/No.of.cellTypes){
        Adjusted.Pvalue[i,j] <- Adjusted.Pvalue[i,j]*No.of.cellTypes        
      }
      else{
        Adjusted.Pvalue[i,j] <- 1.0
      }
    }
  } 
  
  #Adjusted.Pvalue
  #### creating data frame for significant results
  
  #dim(mat)
  #dim(pValue.mat)
  Adjusted.Pvalue
  mat.rowname <- rownames(mat)
  mat.colname <- colnames(mat)
  
  enrichment.result <- data.frame(Gene=character(1),cellType=character(1),Enrichment=numeric(1),pValue=numeric(1),qValue=numeric(1),stringsAsFactors = FALSE)
  
  for(i in 1:dim(pValue.mat)[[1]]){
    for(j in 1:dim(pValue.mat)[[2]]){
      #if(mat[i,j] > Pthreshold){
      if(Adjusted.Pvalue[i,j] < 0.001){  
        newrow <- list(mat.rowname[[i]],mat.colname[[j]],mat[i,j],pValue.mat[i,j],Adjusted.Pvalue[i,j])
        enrichment.result = rbind(enrichment.result,newrow)
      }
    }
  }
  
  write.csv(enrichment.result, file = paste(path,"/enrichment_fisher.csv",sep=""),na = "NA", row.names = TRUE)
  }
  #### Z-transformation 
  reduced.mat <- mat/rowSums(mat)
  #reduced.mat <- apply(mat,2,function(x) (x-min(x))/(max(x)-min(x)))
  #reduced.mat <- apply(mat,2,function(x) ifelse(x < 0.05, 0, x))
  reduced.mat[is.na(reduced.mat)] <- 0
  
  ##### Remove non-significant cell types
  colSum <- colSums(reduced.mat)
  Clist <- c()
  j = 1
  for(i in 1 : length(colSum)){  
    #if(colSum[i] < Ethreshold){
    if(colSum[i] < 0.2){
      Clist[j] <- i
      j = j + 1
    }
  }
  if(!is.null(Clist)){
  reduced.mat <- reduced.mat[,-Clist]
  }
  #rowSum <- rowSums(reduced.mat)
  #reduced.mat <- t(reduced.mat)
  ####### ploting heat map ########  
  # creates a own color palette from blue to red
  my_palette <- colorRampPalette(c("blue", "white", "red"))(n = 299)
  # (optional) defines the color breaks manually for a "skewed" color transition
  col_breaks = c(seq(0,0.2,length=100),  # for red
                seq(0.2,0.4,length=100),              # for yellow
                seq(0.4,1,length=100))              # for green
  #
  
  pathtosave <- paste(path,"/GeneMiner_heatmap.pdf",sep="")
  pdf(paste(path,"/GeneMiner_heatmap.pdf",sep=""),width=8.27,height=11.69)
  
  heatmap.2(reduced.mat, 
            #cellnote = mat,  # same data set for cell labels
            main = "GeneMiner", # heat map title
            xlab = "CellTypes",
            ylab = "Genes",
            cexRow = 0.8,           # Changes the size of col and row font size
            cexCol = 0.8,
            notecol="black",      # change font color of cell labels to black
            density.info="none",  # turns off density plot inside color legend
            trace="none",         # turns off trace lines inside the heat map
            margins =c(10,10),     # widens margins around plot
            col=my_palette,       # use on color palette defined earlier
            breaks=col_breaks,    # enable color transition at specified limits
            dendrogram="both",     # only draw a row dendrogram
            #Colv="NA",           #cluster column
            #Rowv = "NA"
  )  
  dev.off()
}



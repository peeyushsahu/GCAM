palindrome <- function(path,threshold,synonym) {
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
  path1 = paste(path,"firstResult.csv",sep="")
  path2 = paste(path,"synonymList.csv",sep="")
  GeneMinerData <- read.csv(path1,header=TRUE,stringsAsFactor=FALSE)
  SynonymList <- read.csv(path2,header=FALSE,stringsAsFactor=FALSE)
  #GeneMinerData <- read.csv(file.choose(),header=TRUE,stringsAsFactor=FALSE)
  #SynonymList <- read.csv(file.choose(),header=FALSE,stringsAsFactor=FALSE)
  
  #### Joining counts for synonyms 
  if(synonym == 1){
  newMatrix.synonym <- matrix(data=0,nrow=dim(SynonymList)[1],ncol=length(GeneMinerData[1,])-1)
  for(i in 1:dim(SynonymList)[1]){
    for(j in 2:dim(SynonymList)[2]){
      if(SynonymList[i,j]!=""){
      index = which(GeneMinerData[,1] == SynonymList[i,j])
      for(k in 2:dim(GeneMinerData)[2]){
        #print(GeneMinerData[index,k])
        #print(newMatrix.synonym[i,k-1])
        newMatrix.synonym[i,k-1] <- newMatrix.synonym[i,k-1] + GeneMinerData[index,k]
        }
      }
    }
  }
  ### for synonym
  #### Coverting dataframe to matrix  
  rownames(newMatrix.synonym) <- SynonymList[,1]
  GCN <- colnames(GeneMinerData)
  colnames(newMatrix.synonym) <- GCN[-1]
  mat <- newMatrix.synonym
  } 
  else{
    mat <- as.matrix(sapply(GeneMinerData, as.numeric))
    mat <- mat[,-1]
    rownames(mat) <- GeneMinerData[,1]
    #### Coverting dataframe to matrix
    index = c()
    for(i in 1:length(SynonymList[,1])){
      index[i]  = which(GeneMinerData[,1] == SynonymList[,1][[i]])
    }
    mat <- as.matrix(sapply(GeneMinerData[index,], as.numeric))
    mat <- mat[,-1]
    rownames(mat) <- GeneMinerData[index,1]
  } 
  
  #### To find columns with total zero
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
 
  #### Finding row with zero values
  rowSum <- rowSums(mat)
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
  
  pValue.mat <- matrix(0,nrow=dim(mat4pVal)[1],ncol=dim(mat4pVal)[2])
  for(i in 1:dim(mat4pVal)[[1]]){
    for(j in 1:dim(mat4pVal)[[2]]){
      
      a <- mat4pVal[i,j]
      b <- rowSums(mat4pVal)[[i]]-mat4pVal[i,j]
      c <- colSum1[[j]]-mat4pVal[i,j]
      d <- Reduce("+",rowSums(mat4pVal))-(a+b+c)
      #(rowSums(mat4pVal)[[i]]+r.count.s.celltype.a.gene)
      
      contigency <-
        matrix(c(a,b,c,d),
               nrow = 2,
               dimnames =
                 list(c("Perticular.gene", "rest.gene"),
                      c("perticular.cellType", "rest.cellType")))
      
      pval = fisher.test(contigency, alternative = "greater")[[1]]
      pValue.mat[i,j] <- pval
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
  
  dim(mat)
  dim(pValue.mat)
  Adjusted.Pvalue
  mat.rowname <- rownames(mat)
  mat.colname <- colnames(mat)
  
  enrichment.result <- data.frame(Gene=character(1),cellType=character(1),Enrichment=numeric(1),pValue=numeric(1),FDR=numeric(1), stringsAsFactors = FALSE)
  
  for(i in 1:dim(pValue.mat)[[1]]){
    for(j in 1:dim(pValue.mat)[[2]]){
      if(mat[i,j] > 0.1){
        
        newrow <- list(mat.rowname[[i]],mat.colname[[j]],mat[i,j],pValue.mat[i,j],Adjusted.Pvalue[i,j])
        enrichment.result = rbind(enrichment.result,newrow)
      }
    }
  }
  
  write.csv(enrichment.result, file = "/home/peeyush/Desktop/enrichment.csv",na = "NA", row.names = TRUE)
  

  mat <- mat/rowSums(mat)
  reduced.mat <- mat
  
  ##### Remove non-significant cell types 
  colSum <- colSums(reduced.mat)
  Clist <- c()
  j = 1
  for(i in 1 : length(colSum)){  
    if(colSum[i] < 0.2){
      Clist[j] <- i
      j = j + 1
    }
  }
  reduced.mat <- reduced.mat[,-Clist]
  colSum <- colSums(reduced.mat)
  
  ####### ploting heat map ########  
  # creates a own color palette from blue to red
  my_palette <- colorRampPalette(c("blue", "white", "red"))(n = 299)
  
  pdf("/home/peeyush/Desktop/GeneMiner_heatmap_widout_synonym.pdf",width=8.27,height=11.69)
  
  heatmap.2(reduced.mat, 
            #cellnote = mat,  # same data set for cell labels
            main = "GeneMiner", # heat map title
            xlab = "CellTypes",
            ylab = "Genes",
            cexRow = 0.5,           # Changes the size of col and row font size
            cexCol = 0.8,
            notecol="black",      # change font color of cell labels to black
            density.info="none",  # turns off density plot inside color legend
            trace="none",         # turns off trace lines inside the heat map
            margins =c(10,10),     # widens margins around plot
            col=my_palette,       # use on color palette defined earlier
            #breaks=col_breaks,    # enable color transition at specified limits
            dendrogram="both",     # only draw a row dendrogram
            #Colv="NA",           #cluster column
            #Rowv = "NA"
  )  
  dev.off()
}





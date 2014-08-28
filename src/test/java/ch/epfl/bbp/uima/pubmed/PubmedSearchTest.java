package ch.epfl.bbp.uima.pubmed;

import de.bonn.limes.pubmed.PubmedSearch;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class PubmedSearchTest {  

    @Test
    public void testMesh() throws Exception {
	List<Integer> articles = new PubmedSearch().getPubMedIDs("CD44");
	assertTrue(articles.size() > 10000);
    }
}

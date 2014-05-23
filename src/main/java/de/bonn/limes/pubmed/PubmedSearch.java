/*
 * #Copyright (C) Peeyush Sahu <peeyush215[at]gmail.com>
 * #
 * #This program is free software: you can redistribute it and/or modify
 * #it under the terms of the GNU General Public License as published by
 * #the Free Software Foundation, either version 3 of the License, or
 * #(at your option) any later version.
 * #
 * #This program is distributed in the hope that it will be useful,
 * #but WITHOUT ANY WARRANTY; without even the implied warranty of
 * #MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * #GNU General Public License for more details.
 * #
 * #You should have received a copy of the GNU General Public License
 * #along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.bonn.limes.pubmed;

import gov.nih.nlm.ncbi.www.soap.eutils.EUtilsServiceStub;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 
 */
public class PubmedSearch {

    private static final Logger LOG = LoggerFactory.getLogger(PubmedSearch.class);
    private EUtilsServiceStub service;

    public PubmedSearch() throws AxisFault {
        init();
    }

    private void init() throws AxisFault {
        service = new EUtilsServiceStub();
    }

    /**
     * @param query
     * @return a list of PMIDs for the input query
     * @throws java.rmi.RemoteException
     */
    public List<Integer> getPubMedIDs(String query) throws RemoteException {

        EUtilsServiceStub.ESearchRequest req = new EUtilsServiceStub.ESearchRequest();
        req.setDb("pubmed");
        req.setEmail("peeyush215@gmail.com");
        req.setTerm(query);
        req.setRetStart("0");
        req.setTool("GeneMiner");
        req.setRetMax(Integer.MAX_VALUE + "");
        EUtilsServiceStub.ESearchResult res = service.run_eSearch(req);
        int count = new Integer(res.getCount());
        LOG.debug("Found {} ids for query '{}'", count, query);
        List<Integer> articleIds = new ArrayList<>();
        if(count != 0){ //this is added by me
                
        String[] idList = res.getIdList().getId();
        for (String id : idList) {
            articleIds.add(new Integer(id));
        }
        assert (count == articleIds.size()) : "result counts should match, "
                + articleIds.size() + ":" + count;
        return articleIds;
    }
        articleIds.add(0);
        return articleIds;
    }    

    public List<Integer> getPubMedIDs(String query, int maxNumResults) throws RemoteException {

        EUtilsServiceStub.ESearchRequest req = new EUtilsServiceStub.ESearchRequest();
        req.setDb("pubmed");
        req.setEmail("peeyush215@gmail.com");
        req.setTerm(query);
        req.setRetStart("0");
        req.setTool("GeneMiner");
        req.setRetMax(maxNumResults + "");
        EUtilsServiceStub.ESearchResult res = service.run_eSearch(req);
        int count = new Integer(res.getCount());
        LOG.debug("Found {} ids for query '{}'", count, query);

        List<Integer> articleIds = new ArrayList<>();
        String[] idList = res.getIdList().getId();
        for (String id : idList) {
            articleIds.add(new Integer(id));
        }
        assert (count == articleIds.size()) : "result counts should match, "
                + articleIds.size() + ":" + count;
        return articleIds;
    }
}

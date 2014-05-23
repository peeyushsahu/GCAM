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

package de.bonn.limes.entities;

import java.util.ArrayList;

/**
 *
 * @author peeyush
 */
public class EntityTaged {
    
    ArrayList taggedentity;
    Integer PMID;
    
   public ArrayList getTaggedentity() {
        return taggedentity;
    }

    public void setTaggedentity(ArrayList taggedentity) {
        this.taggedentity = taggedentity;
    }

    public Integer getPMID() {
        return PMID;
    }

    public void setPMID(Integer PMID) {
        this.PMID = PMID;
    }
        
}

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

import java.util.TreeMap;

/**
 *
 * @author peeyush
 */
public class Occurrenceobj {
    
    TreeMap<String,Integer> occurrence = new TreeMap();
    String gene="";
    
    public TreeMap<String, Integer> getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(TreeMap<String, Integer> occurrence) {
        this.occurrence = occurrence;
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }
    
}

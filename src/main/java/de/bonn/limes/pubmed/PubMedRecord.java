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

/**
 *
 * @author peeyush
 */
public class PubMedRecord {

    public Integer pubMedID; //Unique id
    protected String title; //Title
    protected Integer yearCreated; //Year
    protected String citation; //Full citation
    protected String abs; //Abstract

    @Override
    public String toString() {
        return pubMedID + "\t" + yearCreated + "\t" + title + "\t" + getCitation() + "\t" + abs;
    }

    /**
     * Obtain publication date of record
     *
     * @return
     */
    public Integer getPubMedID() {
        return pubMedID;
    }
    
    public int getYear() {
        return yearCreated;
    }

    public String getTitle() {
        return title;
    }

    public String getCitation() {
        return citation;
    }

    public String getAbstract() {
        return abs;
    }
}

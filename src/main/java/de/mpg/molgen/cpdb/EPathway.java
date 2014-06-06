/*
 * Copyright (C) 2014 Vishal Siramshetty <srmshtty[at]gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.mpg.molgen.cpdb;

/**
 *
 * @author Vishal Siramshetty <srmshtty[at]gmail.com>
 */
public class EPathway {

    protected String Name;
    protected String Origin;
    protected String Identifier;
    protected int matchedEntities;
    protected int totalEntities;
    protected double Pvalue;
    protected double Qvalue;

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(Name).append("\t").append(Origin);
        return builder.toString();
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String Origin) {
        this.Origin = Origin;
    }

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String Identifier) {
        this.Identifier = Identifier;
    }

    public int getMatchedEntities() {
        return matchedEntities;
    }

    public void setMatchedEntities(int matchedEntities) {
        this.matchedEntities = matchedEntities;
    }

    public int getTotalEntities() {
        return totalEntities;
    }

    public void setTotalEntities(int totalEntities) {
        this.totalEntities = totalEntities;
    }

    public double getPvalue() {
        return Pvalue;
    }

    public void setPvalue(double Pvalue) {
        this.Pvalue = Pvalue;
    }

    public double getQvalue() {
        return Qvalue;
    }

    public void setQvalue(double Qvalue) {
        this.Qvalue = Qvalue;
    }

}

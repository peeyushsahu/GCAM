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
package de.bonn.limes.document;

/**
 *
 * @author peeyush
 */
public class Token {

    private String taggedToken;

    public void setTaggedToken(String taggedToken) {
        this.taggedToken = taggedToken;
    }

    /**
     * Returns the word from the tagged word
     *     
*/
    public void untagToken() {

        int p = taggedToken.indexOf("_");
        this.taggedToken = taggedToken.substring(0, p);
    }

    @Override
    public String toString() {
        return this.taggedToken;
    }

    /**
     * Returns the exact Part Of Speech associated with a tagged word
     *     
* @param taggedToken
     * @return POS
     */
    public String getPOSTag(String taggedToken) {
        int p = taggedToken.indexOf("_");
        taggedToken = taggedToken.substring(p + 1, taggedToken.length());

        return taggedToken;
    }

}

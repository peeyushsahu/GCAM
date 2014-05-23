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
package de.bonn.limes.core;

import com.jmcejuela.bio.jenia.JeniaTagger;
import com.jmcejuela.bio.jenia.common.Sentence;
import de.bonn.limes.document.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peeyush
 */
public class GeniaTagger {

    private List<Token> tokens;
    private List<Sentence> outputs;

    public List<Sentence> getOutputs() {
        return outputs;
    }

    public GeniaTagger(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void tagTokens() {
        boolean dont_tokenize = true;
        outputs = new ArrayList<>();
        for (Token token : tokens) {
            String tok = token.toString();
            Sentence output = JeniaTagger.analyzeAll(tok, dont_tokenize);
            outputs.add(output);
        }
    }

}

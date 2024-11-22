package dev.ikm.elk.snomed.owlel.parser;

/*-
 * #%L
 * SNOMED OWL EL Profile Model and Parser
 * %%
 * Copyright (C) 2023 - 2024 Integrated Knowledge Management
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.owlel.OwlElObjectFactory;
import dev.ikm.elk.snomed.owlel.model.OwlElObject;
import dev.ikm.elk.snomed.owlel.parser.SnomedOfsGrammarParser.OntologyExpressionContext;

public class SnomedOfsParser {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedOfsParser.class);

	private OwlElObjectFactory factory;

	private SnomedOfsGrammarParser parser;

	private RecognitionException syntaxError;

	public RecognitionException getSyntaxError() {
		return syntaxError;
	}

	public SnomedOfsParser(OwlElObjectFactory factory) {
		super();
		this.factory = factory;
	}

	public OntologyExpressionContext parseStatement(String input) {
		SnomedOfsGrammarLexer lexer = new SnomedOfsGrammarLexer(CharStreams.fromString(input));
		parser = new SnomedOfsGrammarParser(new CommonTokenStream(lexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				LOG.error("Input: " + input);
				LOG.error("At " + charPositionInLine + " " + msg);
				LOG.error("");
				syntaxError = e;
			}
		});
		OntologyExpressionContext context = parser.ontologyExpression();
		return context;
	}

	public OwlElObject buildExpression(OntologyExpressionContext context) {
		SnomedOfsVisitor visitor = new SnomedOfsVisitor(factory);
		OwlElObject expr = visitor.visit(context);
		return expr;
	}

	public OwlElObject buildExpression(String input) {
		return buildExpression(parseStatement(input));
	}

	public String toStringTree(OntologyExpressionContext context) {
		return context.toStringTree(parser);
	}

}

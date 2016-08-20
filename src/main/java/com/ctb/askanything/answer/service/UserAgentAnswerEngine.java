/*
 * Copyright 2016 the original author or authors.
 *
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
 */

package com.ctb.askanything.answer.service;

import com.izeye.uaanalyzer.core.domain.UserAgent;
import com.izeye.uaanalyzer.core.service.DefaultUserAgentAnalyzer;
import com.izeye.uaanalyzer.core.service.UserAgentAnalyzer;

import com.ctb.askanything.api.domain.Answer;
import com.ctb.askanything.api.domain.Question;
import com.ctb.askanything.api.service.AnswerEngine;

/**
 * Answer engine analyzing user agents.
 *
 * @author Johnny Lim
 */
public class UserAgentAnswerEngine implements AnswerEngine {

	private static final String TEMPLATE_ANSWER =
			"The user agent is from \"{analyzedUserAgent}\".";
	private static final String PLACEHOLDER_ANALYZED_USER_AGENT = "{analyzedUserAgent}";

	private final UserAgentAnalyzer userAgentAnalyzer = new DefaultUserAgentAnalyzer();

	@Override
	public Answer answer(Question question) {
		String userAgentCandidate = question.getBody();
		UserAgent analyzed = this.userAgentAnalyzer.analyze(userAgentCandidate);
		if (analyzed == UserAgent.NOT_AVAILABLE) {
			return Answer.NOT_AVAILABLE;
		}
		String answer = TEMPLATE_ANSWER.replace(
				PLACEHOLDER_ANALYZED_USER_AGENT, analyzed.toPrettyString());
		return new Answer(question, answer);
	}

}

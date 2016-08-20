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

import org.junit.Test;

import com.ctb.askanything.api.domain.Answer;
import com.ctb.askanything.api.domain.Question;
import com.ctb.askanything.api.service.AnswerEngine;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for user agent answer engine.
 *
 * @author Johnny Lim
 */
public class UserAgentAnswerEngineTests {

	private AnswerEngine answerEngine = new UserAgentAnswerEngine();

	@Test
	public void testAnswer() {
		String userAgentString = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";

		Question question = new Question();
		question.setBody(userAgentString);
		Answer answer = this.answerEngine.answer(question);
		System.out.println(answer);
		assertThat(answer).isNotEqualTo(Answer.NOT_AVAILABLE);

		question = new Question();
		question.setBody("Hello?");
		answer = this.answerEngine.answer(question);
		System.out.println(answer);
		assertThat(answer).isEqualTo(Answer.NOT_AVAILABLE);
	}

}

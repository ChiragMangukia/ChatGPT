package io.chirag.chatgpt;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingsTest {

	@Test
	void greet() {
		assertThat(ChatGPT.askMe("Hello, how are you")).isNotEmpty();
	}

}

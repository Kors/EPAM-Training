package regexp;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static regexp.HtmlSpecialInformationGetter.picRefRegExp;

class HtmlSpecialInformationGetterTest {

	private static HtmlSpecialInformationGetter informationGetter;

	@BeforeAll
	static void setUp() {
		informationGetter = new HtmlSpecialInformationGetter();
	}

	@Test
	void linksOrderTest() {
		assertThat(informationGetter.areLinksOrdered(), is(false));
	}

	// при поиске "по простому" найдено 186 ссылок на рисунки.
	// При оптимизациях надо проверять что мы никакие не потеряли.
	@Test
	void linkRegExpTest() {
		assertThat("Wrong link reg.exp.(Count mismatch)", getLinksCount(), is(186));
	}

	private int getLinksCount() {
		Matcher ma = Pattern.compile(picRefRegExp).matcher(informationGetter.text);
		int counter = 0;
		while (ma.find())
			counter++;
		return counter;
	}

	@Test
	void linksMatch() {
		informationGetter.showSentencesWithLinks();
		assertThat(informationGetter.picBySent, IsEqual.equalTo(getLinks()));
	}

	private List<String> getLinks() {
		List<String> picByAll = new ArrayList<>();
		Matcher ma = Pattern.compile(picRefRegExp).matcher(informationGetter.text);
		while (ma.find())
			picByAll.add(ma.group());
		return picByAll;
	}
}
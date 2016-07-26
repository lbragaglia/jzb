package jzb;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JzbTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void syntax() {
		Builder b = Jzb.object().object("people").object("person").add("firstName", "Guillame", "lastName", "Laforge")
				.object("address").with( // shortcut of .add(...).end()
						"city", "Paris",
						"country", "France",
						"zip", 12345)
				.add("married", true) // alias .and(...)
				.array("conferences").with("JavaOne", "Gr8conf").end().end()
				.array("phoneNumbers")
					// shortcut .withObject(...) alias .andObject(...)
					.object().with("type", "mobile", "number", "111-111-1111")
					.object().with("type", "home", "number", "222-222-2222");
		
		assertThat(b.build(), is("{\"people\":{\"person\":{\"firstName\":\"Guillame\",\"lastName\":\"Laforge\",\"address\":{\"city\":\"Paris\",\"country\":\"France\",\"zip\":12345},\"married\":true,\"conferences\":[\"JavaOne\",\"Gr8conf\"]}},\"phoneNumbers\":[{\"type\":\"mobile\",\"number\":\"111-111-1111\"},{\"type\":\"home\",\"number\":\"222-222-2222\"}]}"));
	}

}

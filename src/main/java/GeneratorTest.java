
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeneratorTest {

    private final Password password = new Password("Geheimnis");

    private final Alphabet firstAlphabet = new Alphabet(true, false,false,false);
    private final Alphabet secondAlphabet = new Alphabet(false,true,true,true);
    private final Generator generator = new Generator(true,false,false,false);

    @Test
    void test1() { assertEquals("Geheimnis", password.toString());}

    @Test
    void test2() { assertEquals(firstAlphabet.getAlphabet(), Alphabet.UPPERCASE_LETTERS);
    }

    @Test
    void test3() {assertEquals(secondAlphabet.getAlphabet(), Alphabet.LOWERCASE_LETTERS + Alphabet.NUMBERS + Alphabet.SYMBOLS);}

    @Test
    void test4() { assertEquals(generator.alphabet.getAlphabet(), Alphabet.UPPERCASE_LETTERS);}

    @Test
    void test5() { assertEquals(generator.alphabet.getAlphabet().length(), 25);
    }

}

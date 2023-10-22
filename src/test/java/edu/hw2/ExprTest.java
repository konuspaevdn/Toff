package edu.hw2;

import edu.hw2.Task1.Addition;
import edu.hw2.Task1.Constant;
import edu.hw2.Task1.Exponent;
import edu.hw2.Task1.Multiplication;
import edu.hw2.Task1.Negate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExprTest {
    @Test
    @DisplayName("Expression initialization")
    void simpleExpressions() {
        var a =  new Constant(3);
        assertThat(a.evaluate()).isEqualTo(3);
        var b = new Constant(a);
        assertThat(b).isEqualTo(a);

        var c = new Negate(a);
        assertThat(c.evaluate()).isEqualTo(-3);
        var ca = new Negate(-5);
        assertThat(ca.evaluate()).isEqualTo(5);

        var d = new Exponent(2, 3);
        assertThat(d.evaluate()).isEqualTo(8);
        var e = new Exponent(c, b);
        assertThat(e.evaluate()).isEqualTo(-27);
        var f = new Exponent(9, new Constant(1.5));
        assertThat(f.evaluate()).isEqualTo(new Constant(27).evaluate());

        var g = new Addition(3, 4);
        assertThat(g.evaluate()).isEqualTo(7);
        var h = new Addition(g, a);
        assertThat(h.evaluate()).isEqualTo(10);
        var i = new Addition(ca, -1);
        assertThat(i.evaluate()).isEqualTo(4);

        var j = new Multiplication(2, 3);
        assertThat(j.evaluate()).isEqualTo(6);
        var k = new Multiplication(-1, j);
        assertThat(k.evaluate()).isEqualTo(-6);
        var l = new Multiplication(g, j);
        assertThat(l.evaluate()).isEqualTo(42);
    }

    @Test
    @DisplayName("Compound expressions")
    void compoundExpressions() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));
        assertThat(res.evaluate()).isEqualTo(37);
    }
}

package tp2;

import static org.junit.Assert.*;
import org.junit.*;

import org.junit.Test;

import grafo.Assert;

public class AlgoritmoSimilaridadTest {

	@Test(expected = IllegalArgumentException.class)
	public void testAlgoritmoSimilaridadVerticeNegativo() {
		AlgoritmoSimilaridad l = new AlgoritmoSimilaridad();
		l.algoritmoSimilaridad(-1);
	}

}

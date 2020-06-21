package fr.ensim.interop.eval.exo1;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AvisServiceTest {

	@Test
	void testEnregistrerAvis() throws IllegalArgumentException, ParseException {
		AvisService avisService = new AvisService();
		avisService.enregistrerAvis("10/10/2020", 5, "test comment", "prov", "1", "1");
		int num = avisService.enregistrerAvis("10/10/2020", 5, "test comment", "prov", "2", "1");
		assertEquals(num, 2);
		
		}

	@Test
	void testListerAvis() throws IllegalArgumentException, ParseException {
		AvisService avisService = new AvisService();
		avisService.enregistrerAvis("10/10/2020", 5, "test comment", "prov", "1", "1");
		avisService.enregistrerAvis("10/10/2020", 5, "test comment", "prov", "2", "2");
		avisService.enregistrerAvis("10/10/2020", 5, "test comment", "prov", "1", "2");
		
		ArrayList<AvisSortant> list = avisService.listerAvis("1");
		assertEquals(list.size(), 2);
		
	}

}

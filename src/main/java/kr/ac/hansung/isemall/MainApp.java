package kr.ac.hansung.isemall;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class MainApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("kr/ac/hansung/conf/beans.xml");

		OfferDAO offerDAO = (OfferDAO) context.getBean("offerDAO");

		System.out.println("The record count is " + offerDAO.getRowCount());

		List<Offer> offerList = offerDAO.getOffers();

		for (Offer offer : offerList) {
			System.out.println(offer);
		}
		
		//삽입
		Offer offer = new Offer();
		offer.setName("nykim");
		offer.setEmail("nykim@hansung.ac.kr");
		offer.setText("an instructor of spring framework");
		
		if(offerDAO.insert(offer)){
			System.out.println("Object is inserted successfully");
		}else{
			System.out.println("Object insert failed");
		}
		
		//업데이트
		offer = offerDAO.getOffer("nykim");
		offer.setName("Namyun Kim");
		if(offerDAO.update(offer)){
			System.out.println("Object is undated successfully");
		}else{
			System.out.println("Object undate failed");
		}
		offer = offerDAO.getOffer("Namyun Kim");
		System.out.println(offer);
		
		//삭제
		if(offerDAO.delete(offer.getId())){
			System.out.println("Object is deleted successfully");
		}else{
			System.out.println("Object delete failed");
		}
		
		context.close();
	}

}

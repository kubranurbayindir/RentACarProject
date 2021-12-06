package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.Brand;
import com.etiya.rentACarSpring.entities.Invoice;
import com.etiya.rentACarSpring.entities.Message;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface MessageDao extends JpaRepository<Message, Integer> {
    @Query(value = "select m.english from messages as m where m.message_id=:messageId", nativeQuery = true)
    String  getEnglishMessageByMessageId(int messageId);


    @Query(value = "select m.turkish from messages as m where m.message_id=:messageId", nativeQuery = true)
    String  getTurkishMessageByMessageId(int messageId);

}

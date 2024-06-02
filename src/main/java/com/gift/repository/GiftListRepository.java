package com.gift.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gift.entity.Cart;
import com.gift.entity.GiftList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class GiftListRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveGiftWithImage(GiftList gift) {
        entityManager.persist(gift);
    }

    public List<GiftList> getAllGifts() {
        return entityManager.createQuery("FROM GiftList", GiftList.class).getResultList();
    }

    public void updateGift(GiftList gift) {
        entityManager.merge(gift);
    }



    public void deleteImage(int id) {
        GiftList gift = entityManager.find(GiftList.class, id);
        if (gift != null) {
            entityManager.remove(gift);
        }
        }
           
    public GiftList getImageById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<GiftList> query = currentSession.createQuery("from GiftList where id=:id", GiftList.class);
        query.setParameter("id", id);   
        return query.getSingleResult();
    }
    
    
        
      
    }

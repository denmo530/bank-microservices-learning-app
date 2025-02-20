package com.demor.cards.mapper;

import com.demor.cards.dto.CardsDto;
import com.demor.cards.entity.Cards;

public class CardsMapper {

    public static CardsDto mapToCardsDto(Cards cards, CardsDto cardsDto) {
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setMobileNumber(cards.getMobileNumber());
        cardsDto.setCardLimit(cards.getCardLimit());
        cardsDto.setBalance(cards.getBalance());
        cardsDto.setCvv(cards.getCvv());
        return cardsDto;
    }

    public static Cards mapToCards(CardsDto cardsDto, Cards cards) {
        cards.setCardNumber(cardsDto.getCardNumber());
        cards.setCardType(cardsDto.getCardType());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        cards.setCardLimit(cardsDto.getCardLimit());
        cards.setBalance(cardsDto.getBalance());
        cards.setCvv(cardsDto.getCvv());
        return cards;
    }
}

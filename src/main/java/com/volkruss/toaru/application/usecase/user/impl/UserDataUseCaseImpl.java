package com.volkruss.toaru.application.usecase.user.impl;

import com.volkruss.toaru.application.usecase.user.UserDataUseCase;
import com.volkruss.toaru.domain.model.character.Character;
import com.volkruss.toaru.domain.model.user.User;
import com.volkruss.toaru.domain.repository.stock.StockRepository;
import com.volkruss.toaru.domain.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDataUseCaseImpl implements UserDataUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Character> getPossessionList() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findByName(username).toUser();

        return this.stockRepository.getUserCharacters(user.getId())
                .stream().map(
                        i -> new Character(i.getId(),i.getName(),i.getLank())
                ).collect(Collectors.toList());
    }
}

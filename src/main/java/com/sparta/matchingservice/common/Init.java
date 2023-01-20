package com.sparta.matchingservice.common;


import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.item.repository.ItemRepository;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import com.sparta.matchingservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Init implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ItemRepository itemRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //user 가입 1~10

        User user1 = User.builder().userName("user1").userRole(UserRole.USER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja")).build();
        userRepository.save(user1);
        User user2 = User.builder().userName("user2").userRole(UserRole.USER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja")).build();
        userRepository.save(user2);

        User user3 = User.builder().userName("user3").userRole(UserRole.USER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja")).build();
        userRepository.save(user3);
        User user4 = User.builder().userName("user4").userRole(UserRole.USER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja")).build();
        userRepository.save(user4);

        User user5 = User.builder().userName("user5").userRole(UserRole.USER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja")).build();
        userRepository.save(user5);
        User user6 = User.builder().userName("user6").userRole(UserRole.USER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja")).build();
        userRepository.save(user6);

        User user7 = User.builder().userName("user7").userRole(UserRole.USER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja")).build();
        userRepository.save(user7);
        User user8 = User.builder().userName("user8").userRole(UserRole.USER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja")).build();
        userRepository.save(user8);

        User user9 = User.builder().userName("user9").userRole(UserRole.USER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja")).build();
        userRepository.save(user9);
        User user10 = User.builder().userName("user10").userRole(UserRole.USER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja")).build();
        userRepository.save(user10);


        //Seller 가입 11~20

        User user11 = User.builder().userName("user11").userRole(UserRole.SELLER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja","우주최강 판매자")).build();
        userRepository.save(user11);
        User user12 = User.builder().userName("user12").userRole(UserRole.SELLER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja","내가 제일 잘팔아")).build();
        userRepository.save(user12);

        User user13 = User.builder().userName("user13").userRole(UserRole.SELLER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja","여기가 제일 싸")).build();
        userRepository.save(user13);
        User user14 = User.builder().userName("user14").userRole(UserRole.SELLER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja","세일 마니아")).build();
        userRepository.save(user14);

        User user15 = User.builder().userName("user15").userRole(UserRole.SELLER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja","앗 신발보다 싸다!")).build();
        userRepository.save(user15);
        User user16 = User.builder().userName("user16").userRole(UserRole.SELLER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja","입은닫고 지갑열고")).build();
        userRepository.save(user16);

        User user17 = User.builder().userName("user17").userRole(UserRole.SELLER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja","내 물건 사세요")).build();
        userRepository.save(user17);
        User user18 = User.builder().userName("user18").userRole(UserRole.SELLER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja","좋은 상품 있어요")).build();
        userRepository.save(user18);

        User user19 = User.builder().userName("user19").userRole(UserRole.SELLER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja","아이맥 팝니다")).build();
        userRepository.save(user19);
        User user20 = User.builder().userName("user20").userRole(UserRole.SELLER).password(passwordEncoder.encode("asdf_1234")).profile(new Profile("banana", "akjskwdkja","맥북 팝니다.")).build();
        userRepository.save(user20);

        //판매 아이템
        Item item1 = Item.builder().itemName("바나나킥").itemContent("바나나킥 팝니다").itemPrice(1000L).stockCount(1L).user(user11).build();
        itemRepository.save(item1);





    }
}

package com.sparta.matchingservice.common;


import com.sparta.matchingservice.item.entity.Item;
import com.sparta.matchingservice.item.repository.ItemRepository;
import com.sparta.matchingservice.order.entity.Order;
import com.sparta.matchingservice.order.entity.OrderStatus;
import com.sparta.matchingservice.order.repository.OrderRepository;
import com.sparta.matchingservice.sellerenrollment.entity.SellerEnrollment;
import com.sparta.matchingservice.sellerenrollment.repository.SellerEnrollmentRepository;
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
    private final SellerEnrollmentRepository sellerEnrollmentRepository;
    private final OrderRepository orderRepository;

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
        Item item2 = Item.builder().itemName("포카칩").itemContent("과자 팝니다").itemPrice(1000L).stockCount(2L).user(user12).build();
        itemRepository.save(item2);
        Item item3 = Item.builder().itemName("새우깡").itemContent("과자 팝니다").itemPrice(1000L).stockCount(3L).user(user13).build();
        itemRepository.save(item3);
        Item item4 = Item.builder().itemName("홈런볼").itemContent("과자 팝니다").itemPrice(1000L).stockCount(4L).user(user14).build();
        itemRepository.save(item4);
        Item item5 = Item.builder().itemName("칸쵸").itemContent("과자 팝니다").itemPrice(1000L).stockCount(5L).user(user15).build();
        itemRepository.save(item5);
        Item item6 = Item.builder().itemName("고래밥").itemContent("과자 팝니다").itemPrice(1000L).stockCount(6L).user(user16).build();
        itemRepository.save(item6);
        Item item7 = Item.builder().itemName("허니버터칩").itemContent("과자 팝니다").itemPrice(1000L).stockCount(7L).user(user17).build();
        itemRepository.save(item7);
        Item item8 = Item.builder().itemName("스윙칩").itemContent("과자 팝니다").itemPrice(1000L).stockCount(8L).user(user18).build();
        itemRepository.save(item8);
        Item item9 = Item.builder().itemName("칙촉").itemContent("과자 팝니다").itemPrice(1000L).stockCount(2L).user(user19).build();
        itemRepository.save(item9);
        Item item10 = Item.builder().itemName("초코파이").itemContent("과자 팝니다").itemPrice(1000L).stockCount(2L).user(user20).build();
        itemRepository.save(item10);

        // 여러 상품 판매자 user15,user16,user17

        Item item11 = Item.builder().itemName("아이폰1").itemContent("아이폰 팝니다").itemPrice(1000L).stockCount(1L).user(user15).build();
        itemRepository.save(item11);
        Item item12 = Item.builder().itemName("아이폰2").itemContent("아이폰 팝니다").itemPrice(1000L).stockCount(2L).user(user15).build();
        itemRepository.save(item12);
        Item item13 = Item.builder().itemName("아이폰3").itemContent("아이폰 팝니다").itemPrice(1000L).stockCount(3L).user(user15).build();
        itemRepository.save(item13);
        Item item14 = Item.builder().itemName("아이폰4").itemContent("아이폰 팝니다").itemPrice(1000L).stockCount(4L).user(user15).build();
        itemRepository.save(item14);
        Item item15 = Item.builder().itemName("아이폰5").itemContent("아이폰 팝니다").itemPrice(1000L).stockCount(5L).user(user15).build();
        itemRepository.save(item15);

        Item item16 = Item.builder().itemName("맥북1").itemContent("맥북 팝니다").itemPrice(1000L).stockCount(1L).user(user16).build();
        itemRepository.save(item16);
        Item item17 = Item.builder().itemName("맥북2").itemContent("맥북 팝니다").itemPrice(1000L).stockCount(2L).user(user16).build();
        itemRepository.save(item17);
        Item item18 = Item.builder().itemName("맥북3").itemContent("맥북 팝니다").itemPrice(1000L).stockCount(3L).user(user16).build();
        itemRepository.save(item18);
        Item item19 = Item.builder().itemName("맥북4").itemContent("맥북 팝니다").itemPrice(1000L).stockCount(4L).user(user16).build();
        itemRepository.save(item19);
        Item item20 = Item.builder().itemName("맥북5").itemContent("맥북 팝니다").itemPrice(1000L).stockCount(5L).user(user16).build();
        itemRepository.save(item20);

        Item item21 = Item.builder().itemName("아이맥1").itemContent("아이맥 팝니다").itemPrice(1000L).stockCount(1L).user(user17).build();
        itemRepository.save(item21);
        Item item22 = Item.builder().itemName("아이맥2").itemContent("아이맥 팝니다").itemPrice(1000L).stockCount(2L).user(user17).build();
        itemRepository.save(item22);
        Item item23 = Item.builder().itemName("아이맥3").itemContent("아이맥 팝니다").itemPrice(1000L).stockCount(3L).user(user17).build();
        itemRepository.save(item23);
        Item item24 = Item.builder().itemName("아이맥4").itemContent("아이맥 팝니다").itemPrice(1000L).stockCount(4L).user(user17).build();
        itemRepository.save(item24);
        Item item25 = Item.builder().itemName("아이맥5").itemContent("아이맥 팝니다").itemPrice(1000L).stockCount(5L).user(user17).build();
        itemRepository.save(item25);
        Item item26 = Item.builder().itemName("아이맥6").itemContent("아이맥 팝니다").itemPrice(1000L).stockCount(6L).user(user17).build();
        itemRepository.save(item26);
        Item item27 = Item.builder().itemName("아이맥7").itemContent("아이맥 팝니다").itemPrice(1000L).stockCount(7L).user(user17).build();
        itemRepository.save(item27);
        Item item28 = Item.builder().itemName("아이맥8").itemContent("아이맥 팝니다").itemPrice(1000L).stockCount(8L).user(user17).build();
        itemRepository.save(item28);
        Item item29 = Item.builder().itemName("아이맥9").itemContent("아이맥 팝니다").itemPrice(1000L).stockCount(9L).user(user17).build();
        itemRepository.save(item29);
        Item item30 = Item.builder().itemName("아이맥10").itemContent("아이맥 팝니다").itemPrice(1000L).stockCount(10L).user(user17).build();
        itemRepository.save(item30);

        // 판매자 등록 대기 user8,user9,user10
        SellerEnrollment sellerEnrollment1 = SellerEnrollment.builder().customer(user8).introduce("이달의 판매왕").build();
        sellerEnrollmentRepository.save(sellerEnrollment1);
        SellerEnrollment sellerEnrollment2 = SellerEnrollment.builder().customer(user9).introduce("판매의 신").build();
        sellerEnrollmentRepository.save(sellerEnrollment2);
        SellerEnrollment sellerEnrollment3 = SellerEnrollment.builder().customer(user10).introduce("판매의 달인").build();
        sellerEnrollmentRepository.save(sellerEnrollment3);



        //주문요청 user 1,2,3
        // user1의 주문요청
        Order order1 = Order.builder().item(item1).orderCount(1L).orderStatus(OrderStatus.WAIT).customer(user1).content("삽니다").build();
        orderRepository.save(order1);
        Order order2 = Order.builder().item(item2).orderCount(1L).orderStatus(OrderStatus.WAIT).customer(user1).content("삽니다").build();
        orderRepository.save(order2);
        Order order3 = Order.builder().item(item3).orderCount(1L).orderStatus(OrderStatus.WAIT).customer(user1).content("삽니다").build();
        orderRepository.save(order3);
        Order order4 = Order.builder().item(item4).orderCount(1L).orderStatus(OrderStatus.WAIT).customer(user1).content("삽니다").build();
        orderRepository.save(order4);
        Order order5 = Order.builder().item(item5).orderCount(1L).orderStatus(OrderStatus.WAIT).customer(user1).content("삽니다").build();
        orderRepository.save(order5);

        //user2의 주문요청
        Order order6 = Order.builder().item(item6).orderCount(1L).orderStatus(OrderStatus.WAIT).customer(user2).content("삽니다").build();
        orderRepository.save(order6);
        Order order7 = Order.builder().item(item7).orderCount(1L).orderStatus(OrderStatus.WAIT).customer(user2).content("삽니다").build();
        orderRepository.save(order7);
        Order order8 = Order.builder().item(item8).orderCount(1L).orderStatus(OrderStatus.WAIT).customer(user2).content("삽니다").build();
        orderRepository.save(order8);
        Order order9 = Order.builder().item(item9).orderCount(1L).orderStatus(OrderStatus.WAIT).customer(user2).content("삽니다").build();
        orderRepository.save(order9);
        Order order10 = Order.builder().item(item10).orderCount(1L).orderStatus(OrderStatus.WAIT).customer(user2).content("삽니다").build();
        orderRepository.save(order10);

        //user3의 주문요청
        Order order11 = Order.builder().item(item11).orderCount(1L).orderStatus(OrderStatus.WAIT).customer(user3).content("삽니다").build();
        orderRepository.save(order11);
        Order order12 = Order.builder().item(item12).orderCount(1L).orderStatus(OrderStatus.WAIT).customer(user3).content("삽니다").build();
        orderRepository.save(order12);
        Order order13 = Order.builder().item(item13).orderCount(1L).orderStatus(OrderStatus.WAIT).customer(user3).content("삽니다").build();
        orderRepository.save(order13);
        Order order14 = Order.builder().item(item14).orderCount(1L).orderStatus(OrderStatus.WAIT).customer(user3).content("삽니다").build();
        orderRepository.save(order14);
        Order order15 = Order.builder().item(item15).orderCount(1L).orderStatus(OrderStatus.WAIT).customer(user3).content("삽니다").build();
        orderRepository.save(order15);












    }
}

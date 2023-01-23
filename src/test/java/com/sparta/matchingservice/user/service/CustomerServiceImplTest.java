package com.sparta.matchingservice.user.service;

import com.sparta.matchingservice.user.dto.ModifyUserProfileRequestDto;
import com.sparta.matchingservice.user.dto.UserProfileResponseDto;
import com.sparta.matchingservice.user.entity.Profile;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import com.sparta.matchingservice.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest
//@ActiveProfiles("test")   << 얘는 왜 빼야지 돌아가는거지...
@Testcontainers  //<< 얘의 역할은 뭐지... 다시 찾아보자.. 테스트하는 빈 컨테이너 만드는건가...? 도커...?
//@DataJpaTest   << 얘는 @SpringBootTest 에 @BootstrapWith 이 들어있는데 @DataJpaTest 도 @BootstrapWith 가 들어있기 때문에 중복되서 안돌아감.
public class CustomerServiceImplTest {


    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerServiceImpl customerService;


    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer<>();


    @Test
    @Transactional
    public void 프로필_수정() {
        // 사용자 정보 넣고
        // 해당 메서드 불러오고
        User user = User.builder()
                .userName("cocoa")
                .userRole(UserRole.USER)
                .profile(Profile.createWithIntroduce()
                        .profileImage("asdasd")
                        .nickName("cocoa").build())
                .password("qwerty")
                .build();

        userRepository.save(user);

        String cocoa = userRepository.findByUserName("cocoa").get().getProfile().getNickName();
        Long cocoaid = userRepository.findByUserName("cocoa").get().getId();

        Assertions.assertSame(cocoa, "cocoa"); // 얘가 테스트 케이스를 만드는 아이이다..

        ModifyUserProfileRequestDto dto = new ModifyUserProfileRequestDto("banana", "susu");

        customerService.modifyUserProfile(dto, cocoaid);
        String s2 = userRepository.findById(user.getId()).get().getProfile().getNickName().toString();
        Assertions.assertSame(s2, "banana");
    }


    @Test
    public void 프로필_수정() {
        // 사용자 정보 넣고
        // 해당 메서드 불러오고
        User user = User.builder()
                .userName("cocoa")
                .userRole(UserRole.USER)
                .profile(Profile.createWithIntroduce()
                        .profileImage("asdasd")
                        .nickName("cocoa").build())
                .password("qwerty")
                .build();

        userRepository.save(user);

        String cocoa = userRepository.findByUserName("cocoa").get().getProfile().getNickName();
        Long cocoaid = userRepository.findByUserName("cocoa").get().getId();

        Assertions.assertSame(cocoa, "cocoa"); // 얘가 테스트 케이스를 만드는 아이이다..

        ModifyUserProfileRequestDto dto = new ModifyUserProfileRequestDto("banana", "susu");

        customerService.modifyUserProfile(dto, cocoaid);
        String s2 = userRepository.findById(user.getId()).get().getProfile().getNickName().toString();
        Assertions.assertSame(s2, "banana");
    }

    
}
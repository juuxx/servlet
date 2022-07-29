package hello.servlet.domain.member;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {

	MemberRepository memberRepository = MemberRepository.getInstance();

	@AfterEach
	void afterEach() {
		memberRepository.clearStore();
	}

	@Test
	void name() {
		//given
		Member member = new Member("hello", 20);

		//when
		Member save = memberRepository.save(member);

		//then
		Member findMember = memberRepository.findById(save.getId());
		assertThat(findMember).isEqualTo(save);
	}

	@Test
	void findAll() {
		//given
		Member member1 = new Member("member1", 20);
		Member member2 = new Member("member2", 30);

		memberRepository.save(member1);
		memberRepository.save(member2);

		//when
		List<Member> result = memberRepository.findAll();

		//then
		assertThat(result.size()).isEqualTo(2);
		assertThat(result).contains(member1, member2);
	}
}
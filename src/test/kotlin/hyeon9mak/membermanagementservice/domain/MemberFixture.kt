package hyeon9mak.membermanagementservice.domain

object MemberFixture {

    fun Member(
        email: String = "jinha3507@gmail.com",
        password: String = "1234!@#abc",
        name: String = "최현구",
        nickname: String = "hyeon9mak",
        phoneNumber: String = "01012345678",
    ): Member = Member.withoutId(
        email = email,
        password = password,
        name = name,
        nickname = nickname,
        phoneNumber = phoneNumber,
    )
}

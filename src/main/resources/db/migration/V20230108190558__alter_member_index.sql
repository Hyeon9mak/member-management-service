ALTER TABLE member DROP INDEX uc_member_email;
ALTER TABLE member DROP INDEX uc_member_nickname;
ALTER TABLE member DROP INDEX uc_member_phone_number;

ALTER TABLE member ADD UNIQUE INDEX ux_member_email (email);
ALTER TABLE member ADD UNIQUE INDEX ux_member_nickname (nickname);
ALTER TABLE member ADD UNIQUE INDEX ux_member_phone_number (phone_number);
ALTER TABLE member ADD UNIQUE INDEX ux_member_email_name_phone_number (email, name, phone_number);

insert into borrowers (id, email, enabled, name)
values (1, 'sql@email.com', true, 'sql');
insert into items (id, creation_date, description, enabled, expiration_date, title, toxic,
                   borrower_id)
values (1, '2000-01-01', 'sql', true, '2000-01-01', 'sql', true, 1);

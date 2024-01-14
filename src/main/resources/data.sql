INSERT INTO artist (alias)
VALUES ('Gydra'), ('Despersion'), ('Mizo'),('THIR13EN');
INSERT INTO label (name)
VALUES ('Neuropunk Records'), ('Neuropunk Forge'), ('КОСТЁР');
INSERT INTO music_distributor(name)
VALUES ('Cyngus Music');
INSERT INTO release (catalog, release_date, release_name, sub_label_id)
VALUES ('NRPFRG009', '2021-08-16', 'Under Control', 2);
insert into release_artists (artist_id, release_id)
VALUES (4, 1);
insert into release_distribution (music_distributor_id, release_id)
VALUES (1, 1);
insert into release_expense (amount, expense_type, quantity, release_id, currency)
VALUES (4000, 'Реклама', 1, 1, 'RUB'), (4000, 'Дистрибьюция', 1, 1, 'RUB');

ALTER TABLE poker_table ADD CONSTRAINT table_name_constraint UNIQUE (table_name);

INSERT INTO poker_table("table_name", "number_of_players", "big_blind") VALUES ('Table1', 4,  10);
INSERT INTO poker_table("table_name", "number_of_players", "big_blind") VALUES ('Table2', 4,  10);
INSERT INTO poker_table("table_name", "number_of_players", "big_blind") VALUES ('Table3', 4,  10);
create table category (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
create table ingredient (id bigint not null auto_increment, amount decimal(19,2), description varchar(255), measure_id bigint, recipe_id bigint, primary key (id)) engine=InnoDB
create table note (id bigint not null auto_increment, note longtext, recipe_id bigint, primary key (id)) engine=InnoDB
create table recipe (id bigint not null, cook_time integer, description varchar(255), difficulty varchar(255), directions longtext, image longblob, prep_time integer, servings integer, source varchar(255), url varchar(255), note_id bigint, primary key (id)) engine=InnoDB
create table recipe_category (recipe_id bigint not null, category_id bigint not null, primary key (recipe_id, category_id)) engine=InnoDB
create table unit_of_measure (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB
alter table ingredient add constraint FKl0rsgb29bbj27gpioyn2hq8ns foreign key (measure_id) references unit_of_measure (id)
alter table ingredient add constraint FKj0s4ywmqqqw4h5iommigh5yja foreign key (recipe_id) references recipe (id)
alter table note add constraint FKaeln1ftj8676mm6e78iu7ibwq foreign key (recipe_id) references recipe (id)
alter table recipe add constraint FKlf335hpxlg22j27pg6mk4k9xt foreign key (note_id) references note (id)
alter table recipe_category add constraint FKqsi87i8d4qqdehlv2eiwvpwb foreign key (category_id) references category (id)
alter table recipe_category add constraint FKcqlqnvfyarhieewfeayk3v25v foreign key (recipe_id) references recipe (id)
create table category (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
create table ingredient (id bigint not null auto_increment, amount decimal(19,2), description varchar(255), measure_id bigint, recipe_id bigint, primary key (id)) engine=InnoDB
create table note (id bigint not null auto_increment, note longtext, recipe_id bigint, primary key (id)) engine=InnoDB
create table recipe (id bigint not null, cook_time integer, description varchar(255), difficulty varchar(255), directions longtext, image longblob, prep_time integer, servings integer, source varchar(255), url varchar(255), note_id bigint, primary key (id)) engine=InnoDB
create table recipe_category (recipe_id bigint not null, category_id bigint not null, primary key (recipe_id, category_id)) engine=InnoDB
create table unit_of_measure (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB
alter table ingredient add constraint FKl0rsgb29bbj27gpioyn2hq8ns foreign key (measure_id) references unit_of_measure (id)
alter table ingredient add constraint FKj0s4ywmqqqw4h5iommigh5yja foreign key (recipe_id) references recipe (id)
alter table note add constraint FKaeln1ftj8676mm6e78iu7ibwq foreign key (recipe_id) references recipe (id)
alter table recipe add constraint FKlf335hpxlg22j27pg6mk4k9xt foreign key (note_id) references note (id)
alter table recipe_category add constraint FKqsi87i8d4qqdehlv2eiwvpwb foreign key (category_id) references category (id)
alter table recipe_category add constraint FKcqlqnvfyarhieewfeayk3v25v foreign key (recipe_id) references recipe (id)
create table category (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
create table ingredient (id bigint not null auto_increment, amount decimal(19,2), description varchar(255), measure_id bigint, recipe_id bigint, primary key (id)) engine=InnoDB
create table note (id bigint not null auto_increment, note longtext, recipe_id bigint, primary key (id)) engine=InnoDB
create table recipe (id bigint not null, cook_time integer, description varchar(255), difficulty varchar(255), directions longtext, image longblob, prep_time integer, servings integer, source varchar(255), url varchar(255), note_id bigint, primary key (id)) engine=InnoDB
create table recipe_category (recipe_id bigint not null, category_id bigint not null, primary key (recipe_id, category_id)) engine=InnoDB
create table unit_of_measure (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB
alter table ingredient add constraint FKl0rsgb29bbj27gpioyn2hq8ns foreign key (measure_id) references unit_of_measure (id)
alter table ingredient add constraint FKj0s4ywmqqqw4h5iommigh5yja foreign key (recipe_id) references recipe (id)
alter table note add constraint FKaeln1ftj8676mm6e78iu7ibwq foreign key (recipe_id) references recipe (id)
alter table recipe add constraint FKlf335hpxlg22j27pg6mk4k9xt foreign key (note_id) references note (id)
alter table recipe_category add constraint FKqsi87i8d4qqdehlv2eiwvpwb foreign key (category_id) references category (id)
alter table recipe_category add constraint FKcqlqnvfyarhieewfeayk3v25v foreign key (recipe_id) references recipe (id)

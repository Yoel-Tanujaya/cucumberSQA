# RequestMapping Server

add person (consume & produce JSON)
update person by id (consume & produce JSON)
delete person by id c(consume & produce JSON)
add phone by person_id (consume & produce JSON)
update phone by phone_id (consume & produce JSON)
delete phone by phone_id (consume & produce JSON)
select all (only produce JSON)
select by id (only produce JSON)

Consume JSON = use StringBuilder to make JSON Request

# Database URL

MYSQL port 3306
Username: root
Password: root
Database Name: sqa

# LIST OF SCENARIOS

Feature: CRUD Person dan Phone
    Scenario: Penambahan Data Person
        Given Ambil jumlah total record saat ini 
        When Ditambahkan record baru firstName:Budi, lastName:Susanto, umur:17 tahun, hp: 987854626272, no ktp: 88888888888
	Then Jumlah total data terakhir adalah record awal + 1

RequestMapping used: select all - add person - select all

Feature: CRUD Person dan Phone
    Scenario: Penghapusan Data Person
        Given Ambil jumlah total record saat ini
        When Hapus record id ke: 1 
	Then Jumlah total data terakhir adalah record awal - 1

RequestMapping used: select all - delete person - select all

Feature: CRUD Person dan Phone
    Scenario: Penambahan dan Pengecekan Data Person
        Given Ambil jumlah total record saat ini
        When Ditambahkan record baru firstName:Manusia, lastName:Badut, umur:20 tahun, hp: 088822221111, no ktp: 11122233344
	When Mengakses data person ke record awal + 1 
	Then Data person yang didapatkan adalah firstName:Manusia, lastName:Badut, umur:20 tahun, hp: 088822221111, no ktp: 11122233344

RequestMapping used: select all - add person - select person - select person

Feature: CRUD Person dan Phone
    Scenario: Pengecekan Data Person yang di update
        Given Akses data person id: 2
        When Update data person id: 2
	Then Data person id: 2 tidak sama dengan data awal

RequestMapping used: select person - update person - select person

Feature: CRUD Person dan Phone
    Scenario: Pengecekan Data Person yang di delete
        Given Ambil jumlah total record saat ini
        When Delete data person id: record awal + 1
	Then Jumlah total data terakhir adalah record awal

RequestMapping used: select all - delete person - select all

Feature: CRUD Person dan Phone
    Scenario: Pengecekan Data Phone yang di update
        Given Ambil record person id ke: 1
        When Update phone id ke: 2 menjadi: 082233334444
	Then Phone id ke: 2 adalah: 082233334444

RequestMapping used: select person - update phone - select person

Feature: CRUD Person dan Phone
    Scenario: Pengecekan Data Person yang di delete
        Given Jumlah total phone record untuk person id ke: 1
        When D (lebih besar dari jumlah record)
	Then Jumlah total data terakhir sama dengan record awal

RequestMapping used: select person - delete phone - select person

Feature: CRUD Person dan Phone
    Scenario: Pengecekan Data Person yang di delete
        Given Ambil record person id ke: 1
        When Delete person id ke: 1
	When Update phone id person ke: 1
	Then Record phone person id ke: 1 = nullValue()

RequestMapping used: select person - delete person - update phone - select person

Feature: CRUD Person dan Phone
    Scenario: Penambahan phone untuk person id2
        Given Jumlah total phone record untuk person id ke: 1
        When Phone id: 1 ditambahkan nomor telepon: 082212341234
	Then Jumlah total phone record person id ke: 1 adalah record awal + 1

RequestMapping used: select person - add phone - select person





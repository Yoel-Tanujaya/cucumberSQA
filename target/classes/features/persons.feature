Feature: CRUD Person dan Phone
    Scenario: Penghapusan Data Person
        Given Ambil jumlah total record saat ini
        When Hapus record id ke: 1 
		Then Data Person id ke: 1 kosong
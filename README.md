Intelij Idea üzerinden yaptım Java 17 kullanarak.
Mysql databasesi kullandım.
İşlemleri postman üzerinden uyguladım.
Öncelikle edit configurations kısmından database kullanıcı adı ve şifreyi tanımlanaız lazım
DATABASE_USERNAME ve DATABASE_PASSWORD kısmı için

postman emirleri:
http://localhost:8080/users/create(kullanıcı oluşturma)
http://localhost:8080/users/1/level-up(seviye atlama)
http://localhost:8080/users/1/enter-tournament(turnuvaya katılım)
http://localhost:8080/users/9/claim-reward(ödül dağıtımı)(mailde sorduğum üzere herhangi bir sonuç ve turnuva gerekmediği için direkt girilen kullanıcıya 5000 coin verdim)
http://localhost:8080/users/9/group-rank?tournamentId=4(![image](https://github.com/semihhasal/dreamgames/assets/72083198/f19979c3-96ed-4316-a7e1-0c4ca7abec4f))(Böyle bir tablo üzerinden testlerimi gerçekleştirdim.
http://localhost:8080/users/group-leaderboard?tournamentId=4(yine aynı tablo üzerinden işlemleri yaptım.)




Projeyi GitFront Üzerinden paylaştım.


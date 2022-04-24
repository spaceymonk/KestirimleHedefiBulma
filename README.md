# Proje Kurulum Dökümanı

Daha etkileyici bir görünüm için bu dokümanı bir Markdown Renderer üzerinde (VS Code gibi) açabilirsiniz.


## Kullanılan Teknolojiler

- Spring Boot Framework
- Apache Kafka
- Zookeeper *(Kafka bağımlılığı)*
- Docker


## Derleme Aşamaları

1. İlgili proje klasörüne gidilir.  
   `cd <path>`
2. Aşağıdaki komut yardımıyla JAR dosyası oluşturulur:  
   `./mvnw -D maven.test.skip=true clean install`
3. Bu aşamalar her iki proje için de uygulanır.

## Çalıştırma Aşamaları

1. Aşağıdaki komut yardımıyla proje ayağa kaldırılabilir:  
   `docker-compose up -d`
2. Sensörlerin ve merkezi birimin durumu ilgili konteynerin kütük kayıtlarına bakılarak gözlenebilir:  
   `docker logs <container-name>`
3. Hesap edilen değere ulaşmak için merkezi birime `GET` isteği atılır:  
   `curl -X GET localhost:8080/`


## Konfigürasyonlar

Uygulamanın parametrelerini değiştirmek için `docker-compose.yml` dosyasındaki ilgili yerler (`environments` altında)
değiştirilir.

Örnek sensör ve hedef koordinasyonlarnın belirtilmesi:

``` JSON
{
  "app.sensor.location.x" : "-5",
  "app.sensor.location.y" : "1",
  "app.target.location.x" : "-1",
  "app.target.location.y" : "5"
}
```

*Dikkat* edilmesi gereken nokta sensörlere kaydedilen hedef koordinatlarının aynı olmasıdır. Aksi takdirde bir kesişim
bulunmayabilir.
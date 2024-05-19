// import io.qameta.allure.Story;
// import io.restassured.http.ContentType;
// import io.restassured.response.Response;
// import org.junit.jupiter.api.Test;
// import utils.PdfUtils;

// import java.io.File;

// import static io.restassured.RestAssured.given;
// import static org.assertj.core.api.Assertions.assertThat;
// import static org.hamcrest.Matchers.containsString;
// import static org.hamcrest.Matchers.equalTo;

// @Story("Download API")
// class DownloadApiTests {
//     PdfUtils pdfUtils = new PdfUtils();
//     @Test
//     void uploadTest() {
//         int petId = 1;
//         String url = String.format("https://petstore.swagger.io/v2/pet/%s/uploadImage", petId);
//         String fileName = "483d9746-ef09-4d7b-8f2e-06b22d12675f.jpg";
//         File file = new File(fileName);

//         given().
//                 when().
//                 multiPart("file", file, "image/jpeg").
//                 contentType("multipart/form-data").
//                 accept("application/json").
//                 post(url).
//                 then().
//                 log().
//                 all().
//                 assertThat().
//                 contentType(ContentType.JSON).
//                 statusCode(200).
//                 body("code", equalTo(200)).
//                 body("type", equalTo("unknown")).
//                 body("message", containsString("File uploaded to ./" + fileName));
//     }

//     @Test
//     void testDownloadHttpClient() {
//         String endpoint = "https://alfabank.servicecdn.ru/site-upload/62/8e/356/rules_promotion_nopaper_izk_v3.pdf";
//         String fileName = "downloadedRulesPromo.pdf";

//         Response response =
//                 given().
//                         when().
//                         get(endpoint).
//                         then().
//                         contentType("application/pdf").
//                         statusCode(200).
//                         extract().response();
//         pdfUtils.savePdf(response, fileName);
//         String pdfText = pdfUtils.readPdf(fileName);
//         assertThat(pdfText).contains("Акция «Безбумажный ИЗК для УЛК».")
//                 .contains("проведения Акции «Безбумажный ИЗК для УЛК»");
//     }
// }

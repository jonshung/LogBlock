#include "HTTPRequestClientTest.hpp"

#include "oatpp-test/web/ClientServerTestRunner.hpp"
#include "oatpp/web/client/HttpRequestExecutor.hpp"

void HTTPRequestTestUnit::onRun() {
    /** Register test components */
    TestApplication test_app;

    /* Create client-server test runner */
    oatpp::test::web::ClientServerTestRunner runner;

    runner.run([this, &runner] {
        OATPP_COMPONENT(std::shared_ptr<oatpp::network::ClientConnectionProvider>, clientConnectionProvider);

        /* Get object mapper component */
        OATPP_COMPONENT(std::shared_ptr<oatpp::data::mapping::ObjectMapper>, objectMapper);

        auto requestExecutor = oatpp::web::client::HttpRequestExecutor::createShared(clientConnectionProvider);
        auto client = HTTPRequestTestClient::createShared(requestExecutor, objectMapper);

        auto response = client->getHome();
        OATPP_ASSERT( (response->getStatusCode() == 404 ) );
        for(auto header : response->getHeaders().getAll()) {
            OATPP_LOGi("TEST_HTTPRequestTestUnit", "Header field: {}={}", header.first.toString(), header.second.toString());
        }
    }, std::chrono::minutes(10)); // timeout 10 minutes

    /* wait all server threads finished */
    std::this_thread::sleep_for(std::chrono::seconds(1));
}
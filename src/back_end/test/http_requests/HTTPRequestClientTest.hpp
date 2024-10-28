#ifndef LOGBLOCK_TEST_HTTP_REQUEST_CLIENT_H
#define LOGBLOCK_TEST_HTTP_REQUEST_CLIENT_H


/** CLIENT  */
#include "oatpp/web/client/ApiClient.hpp"
#include "oatpp/macro/codegen.hpp"

#include OATPP_CODEGEN_BEGIN(ApiClient)

class HTTPRequestTestClient : public oatpp::web::client::ApiClient {

    API_CLIENT_INIT(HTTPRequestTestClient)

    API_CALL("GET", "/", getHome)
    
}; // class HTTPRequestTestClient

#include OATPP_CODEGEN_END(ApiClient)


/** UNIT TEST */
#include "oatpp-test/UnitTest.hpp"

class HTTPRequestTestUnit : public oatpp::test::UnitTest {
public:
    HTTPRequestTestUnit() : UnitTest("TEST_HTTPRequestTestUnit")
    {}

    void onRun() override final;
}; // class HTTPRequestTestUnit




/** TEST CONFIGURATION INCLUDES */
#include "oatpp/web/server/HttpConnectionHandler.hpp"

#include "oatpp/network/virtual_/client/ConnectionProvider.hpp"
#include "oatpp/network/virtual_/server/ConnectionProvider.hpp"
#include "oatpp/network/virtual_/Interface.hpp"

#include "oatpp/json/ObjectMapper.hpp"

#include "oatpp/macro/component.hpp"

#include <memory>

/**
 * Test Components config
 */
class TestApplication {
public:

  /**
   * Create oatpp virtual network interface for test networking
   */
  OATPP_CREATE_COMPONENT(std::shared_ptr<oatpp::network::virtual_::Interface>, virtualInterface)([] {
    return oatpp::network::virtual_::Interface::obtainShared("virtualhost");
  }());

  /**
   * Create server ConnectionProvider of oatpp virtual connections for test
   */
  OATPP_CREATE_COMPONENT(std::shared_ptr<oatpp::network::ServerConnectionProvider>, serverConnectionProvider)([] {
    OATPP_COMPONENT(std::shared_ptr<oatpp::network::virtual_::Interface>, interface);
    return oatpp::network::virtual_::server::ConnectionProvider::createShared(interface);
  }());

  /**
   * Create client ConnectionProvider of oatpp virtual connections for test
   */
  OATPP_CREATE_COMPONENT(std::shared_ptr<oatpp::network::ClientConnectionProvider>, clientConnectionProvider)([] {
    OATPP_COMPONENT(std::shared_ptr<oatpp::network::virtual_::Interface>, interface);
    return oatpp::network::virtual_::client::ConnectionProvider::createShared(interface);
  }());

  /**
   *  Create Router component
   */
  OATPP_CREATE_COMPONENT(std::shared_ptr<oatpp::web::server::HttpRouter>, httpRouter)([] {
    return oatpp::web::server::HttpRouter::createShared();
  }());

  /**
   *  Create ConnectionHandler component which uses Router component to route requests
   */
  OATPP_CREATE_COMPONENT(std::shared_ptr<oatpp::network::ConnectionHandler>, serverConnectionHandler)([] {
    OATPP_COMPONENT(std::shared_ptr<oatpp::web::server::HttpRouter>, router); // get Router component
    return oatpp::web::server::HttpConnectionHandler::createShared(router);
  }());

  /**
   *  Create ObjectMapper component to serialize/deserialize DTOs in Contoller's API
   */
  OATPP_CREATE_COMPONENT(std::shared_ptr<oatpp::data::mapping::ObjectMapper>, apiObjectMapper)([] {
    return std::make_shared<oatpp::json::ObjectMapper>();
  }());

};

#endif // #ifndef LOGBLOCK_TEST_HTTP_REQUEST_CLIENT_H
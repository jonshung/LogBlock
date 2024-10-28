#include "oatpp/network/Server.hpp"
#include "oatpp/Environment.hpp"

#include "App.hpp"

static const char* app_tag = "LogBlock";

void run() {
    /** App has to be registered after Environment initialization */
    App app_handler;

    /** Start server loop */
    app_handler.m_server.getObject()->run();
    
    /** for logging */
    OATPP_COMPONENT(std::shared_ptr<oatpp::network::ServerConnectionProvider>, server_connection_provider);
    OATPP_LOGi(
        app_tag, 
        "LogBlock HTTP server, listening at: {}:{}", 
        server_connection_provider->getProperty("host").toString(), 
        server_connection_provider->getProperty("port").toString()
    );
}

int main() {
    /** init environment */
    oatpp::Environment::init();

    /** run app, loop until destroyed */
    run();

    /** end */
    oatpp::Environment::destroy();
    return 0;
}
#ifndef LOGBLOCK_APP_H
#define LOGBLOCK_APP_H

#include "oatpp/web/server/HttpConnectionHandler.hpp"
#include "oatpp/network/tcp/server/ConnectionProvider.hpp"

#include "oatpp/macro/component.hpp"

#include <memory>

/**
 * @brief This class acts as a collection for components management, it will register a ConnectionProvider that listens to a port,
 * A HttpRouter which routes requests, a HttpConnectionHandler to handle connections from ConnectionProvider. Finally, it registers
 * a Server which passes connections from ConnectionProvider to HttpConnectionHandler
 * 
 */
class App {
public:
    /**
     * @brief Use to host the connection and start listening to a port on socket-level
     * 
     */
    OATPP_CREATE_COMPONENT(std::shared_ptr<oatpp::network::ServerConnectionProvider>, m_connection_provider)(
        [] {
            return oatpp::network::tcp::server::ConnectionProvider::createShared({"0.0.0.0", 8000, oatpp::network::Address::IP_4});
        }()
    );

    /**
     * @brief HTTP Router
     * 
     */
    OATPP_CREATE_COMPONENT(std::shared_ptr<oatpp::web::server::HttpRouter>, m_http_router)(
        [] {
            return oatpp::web::server::HttpRouter::createShared();
        }()
    );

    /**
     * @brief An application level connection handler which parse the HTTP requests and send to HTTP router
     * 
     */
    OATPP_CREATE_COMPONENT(std::shared_ptr<oatpp::network::ConnectionHandler>, m_http_connection_handler)(
        [] {
            OATPP_COMPONENT(std::shared_ptr<oatpp::web::server::HttpRouter>, http_router);
            return oatpp::web::server::HttpConnectionHandler::createShared(http_router);
        }()
    );

    OATPP_CREATE_COMPONENT(std::shared_ptr<oatpp::network::Server>, m_server)(
        [] {
            const OATPP_COMPONENT(std::shared_ptr<oatpp::network::ServerConnectionProvider>, connection_provider);
            const OATPP_COMPONENT(std::shared_ptr<oatpp::network::ConnectionHandler>, connection_handler);
            return oatpp::network::Server::createShared(connection_provider, connection_handler);
        }()
    );
}; // class App
#endif // #ifndef LOGBLOCK_APP_H
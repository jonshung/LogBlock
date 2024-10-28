#include "http_requests/HTTPRequestClientTest.hpp"

#include <iostream>

void run_tests() {
    OATPP_RUN_TEST(HTTPRequestTestUnit);
}

int main() {

    oatpp::Environment::init();

    run_tests();

    std::cout << "\nEnvironment:\n";
    std::cout << "objectsCount = " << oatpp::Environment::getObjectsCount() << "\n";
    std::cout << "objectsCreated = " << oatpp::Environment::getObjectsCreated() << "\n\n";

    oatpp::Environment::destroy();

    return 0;
}
add_executable(logblock_test)

# requiring atleast C++17
target_compile_features(logblock_test PRIVATE cxx_std_17)
target_compile_options(logblock_test PRIVATE
  $<$<CXX_COMPILER_ID:MSVC>:/W4 /WX>
  $<$<NOT:$<CXX_COMPILER_ID:MSVC>>:-Wall -Wextra -Wpedantic -Werror>
)

target_sources(logblock_test
    PRIVATE
    ${CMAKE_CURRENT_SOURCE_DIR}/Tests.cpp
    ${CMAKE_CURRENT_SOURCE_DIR}/http_requests/HTTPRequestClientTest.cpp
)

target_link_libraries(logblock_test
PRIVATE
    oatpp
    oatpp-test
)

target_include_directories(logblock_test
PUBLIC
 $<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}>/http_requests
 $<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}>/
)
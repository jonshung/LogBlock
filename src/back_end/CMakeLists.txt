cmake_minimum_required(VERSION 3.21 FATAL_ERROR)

project(logblock VERSION 1.0 LANGUAGES CXX)
set(CMAKE_VERBOSE_MAKEFILE ON)

### DEPENDENCIES ###
## OAT++ ##
set(BUILD_SHARED_LIBS OFF)
set(OATPP_INSTALL ON)
set(OATPP_BUILD_TESTS OFF)
set(OATPP_LINK_TEST_LIBRARY OFF)
set(OATPP_LINK_ATOMIC ON)
set(OATPP_MSVC_LINK_STATIC_RUNTIME OFF)

add_subdirectory(libs/oatpp)
### DEPENDENCIES ###

add_executable(logblock)

# requiring atleast C++17
target_compile_features(logblock PRIVATE cxx_std_17)
target_compile_options(logblock PRIVATE
  $<$<CXX_COMPILER_ID:MSVC>:/W4 /WX>
  $<$<NOT:$<CXX_COMPILER_ID:MSVC>>:-Wall -Wextra -Wpedantic -Werror>
)
target_compile_definitions(logblock PUBLIC BINARY_PATH="${CMAKE_CURRENT_BINARY_DIR}")

target_sources(logblock
    PRIVATE
    ${CMAKE_CURRENT_SOURCE_DIR}/src/App.cpp
)

target_link_libraries(logblock
PRIVATE
    oatpp
)

target_include_directories(logblock
PUBLIC
 $<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}>/src
)
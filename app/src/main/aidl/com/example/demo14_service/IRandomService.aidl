// IRandomService.aidl
package com.example.demo14_service;

// Declare any non-default types here with import statements

interface IRandomService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     double MyRandom(in long seed);

}

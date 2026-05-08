package com.example.kotlintp2e2

data class Device(
    val id: Long,
    val name: String,
    val data: Specs?
)
/*
[
  {
    "id": "1",
    "name": "Google Pixel 6 Pro",
    "data": {
      "color": "Cloudy White",
      "capacity": "128 GB"
    }
  },
  {
    "id": "2",
    "name": "Apple iPhone 12 Mini, 256GB, Blue",
    "data": null
  },
  {
    "id": "3",
    "name": "Apple iPhone 12 Pro Max",
    "data": {
      "color": "Cloudy White",
      "capacity GB": 512
    }
  },
  {
    "id": "4",
    "name": "Apple iPhone 11, 64GB",
    "data": {
      "price": 389.99,
      "color": "Purple"
    }
  }
 ]
*/
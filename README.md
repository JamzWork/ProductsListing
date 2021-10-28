# Description
Multiple Product listing using API with its detail screen

# Products List To Detail Screen
https://user-images.githubusercontent.com/44348238/139268652-606e7201-c784-4e25-b169-07cc32aabd1b.mov

# App Architecture

**Name** | **Type** | **Description**
--- | --- | ---
app | Kotlin | MainActivity, ApplicationEntry, Theme, Hilt setup.
data | Kotlin | Repositories, consists of remote API's models ans service
di | Kotlin | Retrofit Module
ui | Kotlin | MVVM Architecture that communicates with data module
utils | Kotlin | Helper modules, constants & extensions
navigation | XML | Jetpack Navigation

# API

https://fakestoreapi.com/

### Product Listing (GET)

https://fakestoreapi.com/products

### Product Details (GET)

https://fakestoreapi.com/products/1

# Main libraries used
#### Retrofit
#### Coroutines
#### Navigation Component
#### Dagger - Hilt
#### Shimmer
#### Glide

# Credits
Thanks to [MohammadReza Keikavousi](https://keikaavousi.com/) for providing https://fakestoreapi.com/ API's for listing :)

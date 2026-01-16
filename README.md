<h1>Imtec.ba – QA Automated Testing (Selenium + JUnit)</h1>

Automated end-to-end (E2E) test suite for <b>Imtec.ba</b>, an e-commerce web application for IT equipment
such as laptops, monitors, components, and accessories.

<h2>Project Overview</h2>

This project focuses on automated quality assurance testing of the Imtec.ba web platform.
The test suite verifies core user-facing workflows including authentication, navigation,
search, filtering, shopping cart management, checkout process, and profile-related features.

Target website: https://imtec.ba

<h2>Technologies Used</h2>

<ul>
  <li>Java</li>
  <li>Selenium WebDriver</li>
  <li>JUnit</li>
  <li>Google Chrome</li>
  <li>WebDriverManager</li>
  <li>IntelliJ IDEA</li>
</ul>

<h2>Test Scope</h2>

<ul>
  <li>User Authentication</li>
  <li>Search functionality</li>
  <li>Website navigation</li>
  <li>Product filtering and sorting</li>
  <li>Security checks</li>
  <li>Profile settings</li>
  <li>Footer validation</li>
  <li>Shopping cart management</li>
  <li>Standard checkout process</li>
  <li>Product details page</li>
  <li>Order history</li>
  <li>Wishlist functionality</li>
  <li>Social media links</li>
  <li>Personal data management (GDPR-related features)</li>
  <li>Address management</li>
</ul>

<h2>How It Works</h2>

<ul>
  <li>Selenium WebDriver launches the browser and navigates to the Imtec.ba website</li>
  <li>Test cases simulate real user interactions</li>
  <li>Assertions verify expected system behavior</li>
  <li>Tests are grouped by functionality for easier maintenance</li>
  <li>WebDriverManager automatically manages browser drivers</li>
</ul>

<h2>How to Run Tests</h2>

<b>Using IntelliJ IDEA</b>

<ul>
  <li>Open the project in IntelliJ IDEA</li>
  <li>Right-click on a test class or test package</li>
  <li>Select <b>Run</b></li>
</ul>

<b>Using Maven</b>

<pre>
mvn clean test
</pre>

To run a specific test class:

<pre>
mvn -Dtest=SearchTest test
</pre>

<h2>Test Results Summary</h2>

<ul>
  <li>Total automated tests: 73</li>
  <li>Passed tests: 66</li>
  <li>Failed tests: 7</li>
</ul>

<h2>Known Issues</h2>

<ul>
  <li>Search functionality with partial or non-existing terms</li>
  <li>Pagination navigation inconsistencies</li>
  <li>Missing or inaccessible Terms of Use page</li>
  <li>Boundary validation issues during checkout input</li>
  <li>Wishlist creation and product addition instability</li>
</ul>

<h2>Project Scope</h2>

This project is intended for educational and demonstration purposes.
It demonstrates automated functional testing concepts and does not aim
to replace production-level QA processes.

<h2>Authors</h2>

Tarik Hodžić<br>
Contributor: Haris Sušić

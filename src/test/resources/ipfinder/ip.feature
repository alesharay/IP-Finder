Feature: IP Addresses

  Get all of the IP Addresses

  Scenario Outline: Network address is as expected
    Given an ip address of "<ip_with_CIDR>"
    When I request the Network address for the given IP
    Then the Network address should be "<network_addr>"

    Examples:
      | ip_with_CIDR            | network_addr  |
      | 192.168.41.19/22        | 192.168.40.0  |
      | 192.37.249.220/12       | 192.32.0.0    |
      | 192.16.89.2/30          | 192.16.89.0   |
      | 192.168.1.2/27          | 192.168.1.0   |
      | 10.23.14.9/3            | 0.0.0.0       |

  Scenario Outline: Host count is as expected
    Given an ip address of "<ip_with_CIDR>"
    When I request the available host count
    Then the host count should be <host_count>

    Examples:
      | ip_with_CIDR            | host_count  |
      | 192.168.41.19/22        | 1022        |
      | 192.37.249.220/12       | 1048574     |
      | 192.16.89.2/30          | 2           |
      | 192.168.1.2/27          | 30          |
      | 10.23.14.9/3            | 536870910   |

  Scenario Outline: Broadcast address count is as expected
    Given an ip address of "<ip_with_CIDR>"
    When I request the Broadcast address for the given IP
    Then the Broadcast address should be "<broadcast_addr>"

    Examples:
      | ip_with_CIDR            | broadcast_addr  |
      | 192.168.41.19/22        | 192.168.43.255  |
      | 192.37.249.220/12       | 192.47.255.255  |
      | 192.16.89.2/30          | 192.16.89.3     |
      | 192.168.1.2/27          | 192.168.1.31    |
      | 10.23.14.9/3            | 31.255.255.255  |

  Scenario Outline: Host min address is as expected
    Given an ip address of "<ip_with_CIDR>"
    When I request the Host min address for the given IP
    Then the Host min address should be "<host_min>"

    Examples:
      | ip_with_CIDR            | host_min      |
      | 192.168.41.19/22        | 192.168.40.1  |
      | 192.37.249.220/12       | 192.32.0.1    |
      | 192.16.89.2/30          | 192.16.89.1   |
      | 192.168.1.2/27          | 192.168.1.1   |
      | 10.23.14.9/3            | 0.0.0.1       |

  Scenario Outline: Host max address is as expected
    Given an ip address of "<ip_with_CIDR>"
    When I request the Host max address for the given IP
    Then the Host max address should be "<host_max>"

    Examples:
      | ip_with_CIDR            | host_max        |
      | 192.168.41.19/22        | 192.168.43.254  |
      | 192.37.249.220/12       | 192.47.255.254  |
      | 192.16.89.2/30          | 192.16.89.2     |
      | 192.168.1.2/27          | 192.168.1.30    |
      | 10.23.14.9/3            | 31.255.255.254  |

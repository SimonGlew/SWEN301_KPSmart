## Client/Server Interactions

1. Login
    - **Client** Sends "Login [username] [password]
    - **Server** Sends to specific client accept message, along with list of routes and business figures within string

2. Logout
    - **Client** sends "Logout [username]"
    - **Server** sends accept message for GUI to quit

3. Mail Delivery
    - **Client** sends "Mail Delivery [Weight][Volume][Origin][Destination][Priority]"
    - **Server** broadcasts the new business figures if sucessful, if not sucessful, server will let client know

4. Update Customer Cost (KPSmart Cost)
    - **Client** sends "Update Customer Cost [Weight][Volume][Origin][Destination]"
    - **Server** send accept message back to client

5. Update Transport Cost (Transport Firm Cost)
    - **Client** sends "Update Transport Cost [Weight][Volume][Transport Firm Name][Origin][Destination][Priority][Day of Week][Frequency][Duration]"
    - **Server** broadcasts the new cost/route for clients to update list if sucessful, if not sucessful, server will let client know

6. Discontinue Transport Route
    - **Client** sends "Discontinue Route [Transport Firm Name][Origin][Destination][Priority]"
    - **Server** broadcasts the discontinued route for clients to update list if sucessful, if not sucessful, server will let client know

7. Review Event Logs
    - **Client** sends "Event Log [Query][Number within query]"
    - **Server** sends back string of that log


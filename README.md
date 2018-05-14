## Reserving-Room application

[download](https://github.com/narisasingngam/Reserving-Room/releases/download/1.0/Reserving-Room.jar)

### authors:

Tanasorn Tritawisup(@jampttws) & Narisa Singngam(@narisasingngam)

### Description

Reserving room program is a program for reserving room of the hotel. User can select the date that user want, how many people(child or adult) then it will show the available room. You can select a type of room that you want and also add optional like breakfast and extra bed. The program will show the price that you have to pay and you can select the currency. When customer confirm reserving it will update the program and administrator can see who have reserved room.

### Feature

- Customer can reserve room and add optional(extra bed and breakfast).
- Manager can see all reserved data and can delete room in the case that customer want to cancel.
- Customer can be a member for using 15% member discount.
- Application will convert currency for the customer if there are foreigner.
- After you selected room and clicked confirm buttom, the program will show a list of all thing that you select and show total money.

### How to use

1.First, you have to choose arrive, depart date and insert people.
- on the right angle you can click sign in and sign up button to show that you are a member.
- on the left angle has a manager button.

[![Screen_Shot_2561-05-13_at_1.22.29_AM.png](https://s9.postimg.cc/fjripzhm7/Screen_Shot_2561-05-13_at_1.22.29_AM.png)](https://postimg.cc/image/wx1t4ucx7/)

2.When you click search button the program will show you a room. 

[![Screen_Shot_2561-05-13_at_1.24.40_AM.png](https://s9.postimg.cc/tvfm8ec8v/Screen_Shot_2561-05-13_at_1.24.40_AM.png)](https://postimg.cc/image/sge1job5n/)

3.After you confirm, you have to insert your information and click confirm. Your reserving is finished!. 
 - list of your selected and total money will show on the right side.
 
[![Screen_Shot_2561-05-13_at_1.26.08_AM.png](https://s9.postimg.cc/9o26g7wsv/Screen_Shot_2561-05-13_at_1.26.08_AM.png)](https://postimg.cc/image/dkfic7hsb/)

4.If you click manager button it will show pop up page to confirm password.
- manager can see reserved data and can find reserved list of any customer.

[![Screen_Shot_2561-05-13_at_1.24.00_AM.png](https://s9.postimg.cc/vzzz9myhr/Screen_Shot_2561-05-13_at_1.24.00_AM.png)](https://postimg.cc/image/xf1jyczkr/)

### Design Pattern

 Pattern   |                class               |
 ----------|----------------------------------- |
 Singleton | Total, bookRequest, DatabaseManage |
 ----------|------------------------------------|
 Observer  | Total, Counter                     |
 ----------|------------------------------------|

web service to get rate currency. - https://currencylayer.com

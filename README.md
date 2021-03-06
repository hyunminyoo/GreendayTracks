# GreendayTracks
iTunes 검색 api로 "Greenday" 검색 결과를 보여주는 어플리케이션.
검색 결과와 Favorite 저장 기능이 있다.



# 개발 조건
안드로이드 운영체제
Target API 29 / Min API 19



# 동작 설명
Bottom Navigation으로 나뉘어 있는 두 가지 화면이 있다.

첫 번째 화면은 음악 검색 결과 화면이다.
앱 동작 시 첫 화면으로 보이며, 키워드 "Greenday"에 대한 검색 결과를 보여준다. iTunes API를 통해 받아온 결괏값이다.
각 음악 우측에는 Favorite로 저장 할 수 있는 별 모양 버튼이 있다.
다시 한번 별 모양을 누르면 Favorite을 취소 할 수 있다.


두 번째 화면은 Favorite 화면이다.
첫 화면에서 Bottom Navigation Bar를 통해 넘어 갈 수 있다.
여기서는 이용자가 Favorite로 지정해 놓은 곡들을 모아 보여준다.
Favorite에 대한 정보는 로컬 데이터베이스에 저장되기 때문에 앱의 생명주기와 상관없이 값을 잃지 않는다.
(단, 어플을 삭제할 경우에는 저장된 내용도 같이 삭제 된다.)
* Favorite 화면에서 별 모양은 어떤 기능도 하지 않는다. Favorite의 저장과 취소는 모두 음악 검색화면에서 이루어져야 한다.





# 남아있는 버그
Favorite 버튼을 통해서 즐겨찾기를 저장하는 경우, 선택된 곡의 별은 즉시 색이 칠해지면서 Favorite에 저장되었음을 보여준다.
그러나 다시 별 모양을 눌러서 저장을 취소하는 경우에는, 저장 취소는 즉시 이루어지지만, UI 반영은 다음 새로 고침 때 적용된다.
즉, 별 모양을 다시 눌러 Favorite 저장을 취소한 경우에는 Favorite 모음 화면으로 가보면 잘 취소 된 것을 알 수 있고, 다시 음악 검색 결과 창으로 오면 음악 정보 옆에 별 모양이 변해 있는 것을 알 수 있다.

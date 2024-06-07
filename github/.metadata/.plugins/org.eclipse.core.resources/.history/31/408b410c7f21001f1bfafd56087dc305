package com.example.restapi.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.dto.MemberDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/msg")
public class MessageController {

	@GetMapping("/test")
	public String test() {
		return "test";
	}

	@GetMapping("/student")
	public String studentMsg() {
		return "유리하다고 교만하지 말고,<br>" + "불리하다고 비굴하지 말라.<br>" + "<br>" + "자기가 아는 대로 진실만을 말하며,<br>" + "주고받는 말마다 악을 막아,<br>"
				+ "듣는 이에게 편안함과 기쁨을 주어라.r<br>" + "<br>" + "무엇을 들었다고 쉽게 행동하지 말고,<br>" + "그것이 사실인지 깊이 생각하여<br>"
				+ "이치가 명확할 때 과감히 행동하라.<br>" + "<br>" + "제 몸 위해 턱 없이 악행하지 말고,<br>" + "핑계대어 정법을 어기지 말며,<br>"
				+ "지나치게 인색하지 말고,<br>" + "성 내거나 질투하지 말라.<br>" + "<br>" + "이기심을 채우고자 정의를 등지지 말고,<br>"
				+ "원망을 원망으로 갚지 말라.<br>" + "<br>" + "이익을 위해 남을 모함하지 말라.<br>" + "<br>" + "객기 부려 만용하지 말고,<br>"
				+ "허약하여 비겁하지 말며,<br>" + "지혜롭게 중도의 길을 가라.<br>" + "<br>" + "이것이 지혜로운 이의 모습이니,<br>" + "사나우면 남들이 꺼려하고,<br>"
				+ "나약하면 남이 업신 여기나니,<br>" + "사나움과 나약함을 버려 중도를 지켜라.<br>" + "<br>" + "벙어리처럼 침묵하고 임금님처럼 말하며,<br>"
				+ "눈처럼 냉정하고 불처럼 뜨거워라.<br>" + "<br>" + "태산 같은 자부심을 갖고,<br>" + "누운 풀처럼 자기를 낮추어라.<br>" + "<br>"
				+ "임금처럼 위엄을 갖추고,<br>" + "구름처럼 한가로워라.<br>" + "<br>" + "역경을 참아 이겨내고.<br>" + "형편이 잘 풀릴 때를 조심하라.<br>"
				+ "<br>" + "재물을 오물처럼 볼 줄도 알고.<br>" + "터지는 분노를 잘 다스려라.<br>" + "<br>" + "때로는 마음껏 풍류를 즐기고,<br>"
				+ "사자처럼 두려워 할 줄 알고,<br>" + "호랑이처럼 무섭고 사나워라.<br>" + "<br>" + "때와 처지를 살필 줄 알고,<br>"
				+ "부귀와 쇠망이 교차함을 알라.<br>" + "이것이 지혜로운 불자의 삶이니라.";
	}

	@GetMapping("/get")
	public String get() {
		System.out.println("get.................");
		String result = "";
		try {
			// 요청할 URL을 정의합니다.
			URL url = new URL("http://localhost:8088/api?num=1");

			// URL 객체에서 HttpURLConnection 객체를 얻습니다.
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// 요청 방식을 GET으로 설정합니다.
			connection.setRequestMethod("GET");

			// 응답 코드를 확인합니다.
			int responseCode = connection.getResponseCode();
			System.out.println("Response Code: " + responseCode);

			// 응답 코드를 확인한 후에 데이터 읽기를 시작합니다.
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// 응답 내용을 출력합니다.
				System.out.println("Response Content: " + response.toString());
				result = response.toString();
			} else {
				System.out.println("GET request not worked");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	@PostMapping("/post")
	public String post(@RequestBody MemberDto memberDto)  {
		String result = "";
		try {
            // URL 설정
            URL url = new URL("http://localhost:8088/post");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // HTTP 메서드 설정
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            // ObjectMapper 객체 생성
            ObjectMapper objectMapper = new ObjectMapper();

            // MemberDto 객체를 JSON 문자열로 변환
            String jsonInputString = objectMapper.writeValueAsString(memberDto);

            // JSON 데이터를 요청 본문에 쓰기
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 응답 코드 확인
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 응답 데이터 읽기
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = in.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                in.close();

                // 응답 출력
                result = response.toString();
                System.out.println("Response: " + response.toString());
            } else {
                System.out.println("POST request not worked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return result;
	}
}
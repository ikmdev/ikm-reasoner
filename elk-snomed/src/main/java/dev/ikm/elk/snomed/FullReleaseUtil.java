package dev.ikm.elk.snomed;

import java.io.IOException;

/*-
 * #%L
 * ELK Integration with SNOMED
 * %%
 * Copyright (C) 2023 - 2025 Integrated Knowledge Management
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FullReleaseUtil {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(FullReleaseUtil.class);

	private static record Line(String id, int effectiveTime, String text) {
	}

	/**
	 * Return a stream of lines for this version. Does not include the header line.
	 * 
	 * @param file
	 * @param version
	 * @return
	 * @throws Exception
	 */
	public static Stream<String> getVersion(Path file, int version) throws IOException {
		// id effectiveTime ...
		HashMap<String, Line> id_lines = new LinkedHashMap<>();
		try (Stream<String> st = Files.lines(file)) {
			st.skip(1).map(line -> {
				String[] fields = line.split("\\t");
				return new Line(fields[0], Integer.parseInt(fields[1]), line);
			}).filter(line -> line.effectiveTime <= version) // effectiveTime
					.forEach(line -> {
						Line prior = id_lines.putIfAbsent(line.id(), line);
						if (prior != null) {
							if (line.effectiveTime() > prior.effectiveTime())
								id_lines.put(line.id(), line);
						}
					});
		}
		return id_lines.values().stream().map(line -> line.text());
	}

}
